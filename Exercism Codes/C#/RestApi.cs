using System.Text.Json;
using System.Text.Json.Serialization;

public class RestApi
{
    private List<User> userJson;

    public RestApi(string database)
    {
        userJson = JsonSerializer.Deserialize<List<User>>(database)!;
    }

    public string Get(string url, string? payload = null)
    {
        if (url == "/users")
        {
            if (payload == null)
            {
                return JsonSerializer.Serialize(userJson);
            }
            else
            {
                List<User> result = new List<User>();
                var p = JsonSerializer.Deserialize<Dictionary<string, List<string>>>(payload)!;
                List<string> names = p["users"];
                foreach (var user in userJson)
                {
                    if (names.Contains(user.name))
                    {
                        result.Add(user);
                    }
                }
                return JsonSerializer.Serialize(result);
            }
        }
        return "";
    }

    public string Post(string url, string payload)
    {
        if (url == "/add")
        {
            string name = JsonSerializer.Deserialize<Dictionary<string, string>>(payload)!["user"];
            User user = new User(name, new Dictionary<string, double>(), new Dictionary<string, double>(), 0);
            userJson.Add(user);
            return JsonSerializer.Serialize(user);
        }
        if (url == "/iou")
        {
            List<User> jsonArray = new List<User>();
            Transaction transaction = JsonSerializer.Deserialize<Transaction>(payload)!;
            string lender = transaction.lender;
            string borrower = transaction.borrower;
            double amount = transaction.amount;
            foreach (var user in userJson)
            {
                string name = user.name;
                if (borrower == name)
                {
                    var owes = user.owes;
                    var owedBy = user.owed_by;

                    double balance = (owedBy.ContainsKey(lender) ? owedBy[lender] : 0) - amount;

                    if (balance == 0)
                    {
                        owes.Remove(lender);
                        owedBy.Remove(lender);
                    }
                    if (balance < 0)
                    {
                        owes[lender] = -balance;
                        owedBy.Remove(lender);
                    }
                    if (balance > 0)
                    {
                        owedBy[lender] = balance;
                    }
                    user.balance = user.balance - amount;
                    user.owes = Sort(user.owes);
                    user.owed_by = Sort(user.owed_by);
                    jsonArray.Add(user);
                }
                if (lender == name)
                {
                    var owes = user.owes;
                    var owedBy = user.owed_by;

                    double balance = (owes.ContainsKey(borrower) ? owes[borrower] : 0) - amount;

                    if (balance == 0)
                    {
                        owedBy.Remove(borrower);
                        owes.Remove(borrower);
                    }
                    if (balance < 0)
                    {
                        owedBy[borrower] = -balance;
                        owes.Remove(borrower);
                    }
                    if (balance > 0)
                    {
                        owes[borrower] = balance;
                    }
                    user.balance = user.balance + amount;
                    user.owes = Sort(user.owes);
                    user.owed_by = Sort(user.owed_by);
                    jsonArray.Add(user);
                }
            }
            return JsonSerializer.Serialize(jsonArray);
        }
        return "";
    }

    private Dictionary<string, double> Sort(Dictionary<string, double> el)
    {
        return el.OrderBy(pair => pair.Key).ToDictionary(pair => pair.Key, pair => pair.Value);
    }

    class User
    {
        public string name { get; set; }
        public Dictionary<string, double> owes { get; set; }
        public Dictionary<string, double> owed_by { get; set; }
        public double balance { get; set; }

        [JsonConstructor]
        public User(string name, Dictionary<string, double> owes, Dictionary<string, double> owed_by, double balance)
        {
            this.name = name;
            this.owes = owes;
            this.owed_by = owed_by;
            this.balance = balance;
        }
    }

    class Transaction
    {
        public string lender { get; set; }
        public string borrower { get; set; }
        public double amount { get; set; }

        [JsonConstructor]
        public Transaction(string lender, string borrower, double amount)
        {
            this.lender = lender;
            this.borrower = borrower;
            this.amount = amount;
        }
    }
}
