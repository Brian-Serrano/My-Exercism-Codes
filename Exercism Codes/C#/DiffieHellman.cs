using System.Numerics;

public static class DiffieHellman
{
    public static BigInteger PrivateKey(BigInteger primeP) 
    {
        return new BigInteger(new Random().Next(1, (int)primeP));
    }

    public static BigInteger PublicKey(BigInteger primeP, BigInteger primeG, BigInteger privateKey) 
    {
        return BigInteger.ModPow(primeG, (int)privateKey, primeP);
    }

    public static BigInteger Secret(BigInteger primeP, BigInteger publicKey, BigInteger privateKey) 
    {
        return BigInteger.ModPow(publicKey, (int)privateKey, primeP);
    }
}