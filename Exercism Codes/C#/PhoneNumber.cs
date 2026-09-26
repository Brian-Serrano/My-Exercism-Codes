using System.Globalization;
using System.Text.RegularExpressions;

public class PhoneNumber
{
    public static string Clean(string phoneNumber)
    {
        string n = Regex.Replace(phoneNumber, "[\\s\\-()\\.+]", "");
        if (n.Length == 11)
        {
            if (n[0] == '1')
            {
                n = n.Substring(1);
            }
            else
            {
                throw new ArgumentException("11 digits must start with 1");
            }
        }
        else if (n.Length > 11)
        {
            throw new ArgumentException("must not be greater than 11 digits");
        }
        else if (n.Length < 10)
        {
            throw new ArgumentException("must not be fewer than 10 digits");
        }

        if (n.Any(c => char.GetUnicodeCategory(c) >= UnicodeCategory.ConnectorPunctuation &&
           char.GetUnicodeCategory(c) <= UnicodeCategory.OtherPunctuation))
        {
            throw new ArgumentException("punctuations not permitted");
        }
        if (n.Any(c => char.IsLetter(c)))
        {
            throw new ArgumentException("letters not permitted");
        }
        if (n[0] == '0')
        {
            throw new ArgumentException("area code cannot start with zero");
        }
        if (n[0] == '1')
        {
            throw new ArgumentException("area code cannot start with one");
        }
        if (n[3] == '0')
        {
            throw new ArgumentException("exchange code cannot start with zero");
        }
        if (n[3] == '1')
        {
            throw new ArgumentException("exchange code cannot start with one");
        }
        return n;
    }
}