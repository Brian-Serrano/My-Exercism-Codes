using System;
using System.Collections.Generic;

public class FacialFeatures
{
    public string EyeColor { get; }
    public decimal PhiltrumWidth { get; }

    public FacialFeatures(string eyeColor, decimal philtrumWidth)
    {
        EyeColor = eyeColor;
        PhiltrumWidth = philtrumWidth;
    }

    public override bool Equals(object obj) => obj is FacialFeatures features &&
               EyeColor == features.EyeColor &&
               PhiltrumWidth == features.PhiltrumWidth;

    public override int GetHashCode() => HashCode.Combine(EyeColor, PhiltrumWidth);
}

public class Identity
{
    public string Email { get; }
    public FacialFeatures FacialFeatures { get; }

    public Identity(string email, FacialFeatures facialFeatures)
    {
        Email = email;
        FacialFeatures = facialFeatures;
    }

    public override bool Equals(object obj) => obj is Identity identity &&
               Email == identity.Email &&
               EqualityComparer<FacialFeatures>.Default.Equals(FacialFeatures, identity.FacialFeatures);

    public override int GetHashCode() => HashCode.Combine(Email, FacialFeatures);

    public bool DefaultEquals(object obj) => base.Equals(obj);
}

public class Authenticator
{
    private readonly HashSet<Identity> identitiesRegistered = new();

    public static bool AreSameFace(FacialFeatures faceA, FacialFeatures faceB) => faceA.Equals(faceB);

    public bool IsAdmin(Identity identity) => identity.Equals(new Identity("admin@exerc.ism", new FacialFeatures("green", 0.9m)));

    public bool Register(Identity identity) => identitiesRegistered.Add(identity);

    public bool IsRegistered(Identity identity) => identitiesRegistered.Contains(identity);

    public static bool AreSameObject(Identity identityA, Identity identityB) => identityA.DefaultEquals(identityB);
}
