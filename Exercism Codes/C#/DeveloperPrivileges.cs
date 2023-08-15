using System;
using System.Collections.Generic;

public class Authenticator
{
    private Identity CreateIdentity(string email, string eyeColor, decimal philtrumWidth, List<string> nameAndAddress) => new Identity
    {
        Email = email,
        FacialFeatures = new FacialFeatures
        {
            EyeColor = eyeColor,
            PhiltrumWidth = philtrumWidth
        },
        NameAndAddress = nameAndAddress
    };

    public Identity Admin { 
        get
        {
            return CreateIdentity("admin@ex.ism", "green", 0.9m, new List<string> { "Chanakya", "Mumbai", "India" });
        }
    }

    public IDictionary<string, Identity> Developers { 
        get
        {
            return new Dictionary<string, Identity>
            {
                { "Bertrand", CreateIdentity("bert@ex.ism", "blue", 0.8m, new List<string> { "Bertrand", "Paris", "France" }) },
                { "Anders", CreateIdentity("anders@ex.ism", "brown", 0.85m, new List<string> { "Anders", "Redmond", "USA" }) }
            };
        }
    }
}

//**** please do not modify the FacialFeatures class ****
public class FacialFeatures
{
    public string EyeColor { get; set; }
    public decimal PhiltrumWidth { get; set; }
}

//**** please do not modify the Identity class ****
public class Identity
{
    public string Email { get; set; }
    public FacialFeatures FacialFeatures { get; set; }
    public IList<string> NameAndAddress { get; set; }
}
