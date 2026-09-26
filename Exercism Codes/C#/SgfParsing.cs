using System.Text.RegularExpressions;

public class SgfTree
{
    public SgfTree(IDictionary<string, string[]> data, params SgfTree[] children)
    {
        Data = data;
        Children = children;
    }

    public IDictionary<string, string[]> Data { get; }
    public SgfTree[] Children { get; set; }

    public override string ToString()
    {
        return $"Data: {string.Join(", ", Data.Select(x => $"{x.Key}=[{string.Join(", ", x.Value)}]"))}, " +
               $"Children: {Children.Length}";
    }
}

public class Property
{
    public string Label { get; set; }
    public string Kind { get; set; }

    public Property(string label, string kind)
    {
        Label = label;
        Kind = kind;
    }
}

public class SgfParser
{
    public static SgfTree ParseTree(string input)
    {
        if (input.Length == 0 || input[0] != '(' || input[^1] != ')')
        {
            throw new ArgumentException("tree missing");
        }
        SgfTree? sgf = ConstructNode(null, input.Replace("\\[", "[").Replace("\\]", "]")[1..^1]);
        if (sgf == null)
        {
            throw new ArgumentException("tree with no nodes");
        }
        return sgf;
    }

    private static SgfTree? ConstructNode(SgfTree? node, string input)
    {
        string? matcher = GetNode(input);
        List<string> parenthesis = ParseParenthesis(input);
        if (!string.IsNullOrEmpty(matcher))
        {
            node = ParseNode(matcher);
            SgfTree? childNode = ConstructNode(node, input.Replace(matcher, ""));
            if (childNode != null)
            {
                node.Children = node.Children.Concat([childNode]).ToArray();
            }
            return node;
        }
        if (parenthesis.Count > 0)
        {
            foreach (string par in parenthesis)
            {
                string extractPar = par[1..^1];
                SgfTree? childNode = ConstructNode(ParseNode(extractPar), extractPar);
                if (childNode != null)
                {
                    node.Children = node.Children.Concat([childNode]).ToArray();
                }
            }
        }
        return null;
    }

    private static string? GetNode(string input)
    {
        if (input.Length > 0 && input[0] != ';')
        {
            return null;
        }

        for (int i = 0; i < input.Length; i++)
        {
            if (i > 0 && i < input.Length && input.Substring(i - 1, 2) == "(;")
            {
                return input.Substring(0, i - 1);
            }
            if (i > 0 && i < input.Length && input.Substring(i - 1, 2) == "];")
            {
                return input.Substring(0, i);
            }
        }
        return input;
    }

    private static List<string> ParseParenthesis(string input)
    {
        List<string> nodes = new List<string>();

        while (!string.IsNullOrEmpty(input))
        {
            if (!input.StartsWith("(;"))
            {
                break;
            }

            int counter = 1;

            for (int i = 2; i < input.Length; i++)
            {
                if (input.Substring(i).StartsWith("(;"))
                {
                    counter++;
                }
                else if (input[i] == ')')
                {
                    counter--;
                }

                if (counter == 0)
                {
                    nodes.Add(input.Substring(0, i + 1));
                    input = input.Substring(i + 1);
                    break;
                }
            }
        }
        return nodes;
    }

    private static SgfTree ParseNode(string input)
    {
        IDictionary<string, List<string>> properties = new Dictionary<string, List<string>>();
        List<Property> matcher = GetMatches(input.Length >= 1 ? input[1..] : string.Empty);
        string? key = null;
        foreach (Property match in matcher)
        {
            if (match.Kind == "key")
            {
                if (match.Label.ToUpper() == match.Label)
                {
                    key = match.Label;
                }
                else
                {
                    throw new ArgumentException("property must be in uppercase");
                }
            }
            if (match.Kind == "value")
            {
                if (properties.ContainsKey(key))
                {
                    properties[key].Add(MapPropertyValue(match.Label));
                }
                else
                {
                    properties[key] = new List<string> { MapPropertyValue(match.Label) };
                }
            }
        }

        if (key != null && !properties.ContainsKey(key))
        {
            throw new ArgumentException("properties without delimiter");
        }

        return new SgfTree(properties.ToDictionary(x => x.Key, x => x.Value.ToArray()));
    }

    private static string MapPropertyValue(string input)
    {
        List<char> text = input[1..^1].ToCharArray().ToList();
        int i = 0;

        while (i < text.Count)
        {
            if (i > 0 && (text[i] == '\\' || text[i] == 't' || text[i] == 'n') && text[i - 1] == '\\')
            {
                text.RemoveAt(i - 1);
                continue;
            }
            if (i > 0 && text[i] == '\t' && text[i - 1] == '\\')
            {
                text.RemoveAt(i - 1);
                text.RemoveAt(i - 1);
                text.Insert(i - 1, ' ');
                continue;
            }
            if (i > 0 && text[i] == '\n' && text[i - 1] == '\\')
            {
                text.RemoveAt(i - 1);
                text.RemoveAt(i - 1);
                continue;
            }
            i++;
        }
        return Regex.Replace(string.Join("", text), "\\t", " ");
    }

    private static List<Property> GetMatches(string input)
    {
        List<Property> result = new List<Property>();
        int start = 0;

        for (int i = 0; i < input.Length; i++)
        {
            if (char.IsLetter(input[i]))
            {
                if (i == 0)
                {
                    start = i;
                    continue;
                }
                if (i > 0 && input[i - 1] == ']' && input[start] == '[')
                {
                    if (!HasClosingBeforeOpening(input, i))
                    {
                        result.Add(new Property(input.Substring(start, i - start), "value"));
                        start = i;
                        continue;
                    }
                }
            }
            if (input[i] == '[')
            {
                if (i > 0 && input[i - 1] == ']' && input[start] == '[')
                {
                    result.Add(new Property(input.Substring(start, i - start), "value"));
                    start = i;
                    continue;
                }
                if (i > 0 && char.IsLetter(input[i - 1]) && char.IsLetter(input[start]))
                {
                    result.Add(new Property(input.Substring(start, i - start), "key"));
                    start = i;
                    continue;
                }
            }
        }

        if (input.Length > 0 && input[^1] == ']')
        {
            result.Add(new Property(input[start..], "value"));
        }
        if (input.Length >= 1 && input.Length <= 2 && input.All(char.IsLetter))
        {
            result.Add(new Property(input, "key"));
        }

        return result;
    }

    private static bool HasClosingBeforeOpening(string input, int index)
    {
        for (int i = index; i < input.Length; i++)
        {
            if (input[i] == ']')
            {
                return true;
            }
            if (input[i] == '[')
            {
                return false;
            }
        }

        return false;
    }
}