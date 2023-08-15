using System;

public struct CurrencyAmount
{
    private decimal amount;
    private string currency;

    public CurrencyAmount(decimal amount, string currency)
    {
        this.amount = amount;
        this.currency = currency;
    }

    // TODO: implement equality operators
    public static bool operator ==(CurrencyAmount lhs, CurrencyAmount rhs)
    {
        return lhs.currency == rhs.currency ? lhs.amount == rhs.amount : throw new ArgumentException();
    }

    public static bool operator !=(CurrencyAmount lhs, CurrencyAmount rhs)
    {
        return lhs.currency == rhs.currency ? lhs.amount != rhs.amount : throw new ArgumentException();
    }

    // TODO: implement comparison operators
    public static bool operator >(CurrencyAmount lhs, CurrencyAmount rhs)
    {
        return lhs.currency == rhs.currency ? lhs.amount > rhs.amount : throw new ArgumentException();
    }

    public static bool operator <(CurrencyAmount lhs, CurrencyAmount rhs)
    {
        return lhs.currency == rhs.currency ? lhs.amount < rhs.amount : throw new ArgumentException();
    }

    // TODO: implement arithmetic operators
    public static CurrencyAmount operator +(CurrencyAmount lhs, CurrencyAmount rhs)
    {
        return lhs.currency == rhs.currency ? new CurrencyAmount(lhs.amount + rhs.amount, lhs.currency) : throw new ArgumentException();
    }

    public static CurrencyAmount operator -(CurrencyAmount lhs, CurrencyAmount rhs)
    {
        return lhs.currency == rhs.currency ? new CurrencyAmount(lhs.amount - rhs.amount, lhs.currency) : throw new ArgumentException();
    }

    public static CurrencyAmount operator *(CurrencyAmount lhs, decimal rhs)
    {
        return new CurrencyAmount(lhs.amount * rhs, lhs.currency);
    }

    public static CurrencyAmount operator /(CurrencyAmount lhs, decimal rhs)
    {
        return new CurrencyAmount(lhs.amount / rhs, lhs.currency);
    }

    // TODO: implement type conversion operators
    public static explicit operator double(CurrencyAmount lhs)
    {
        return (double)lhs.amount;
    }

    public static implicit operator decimal(CurrencyAmount lhs)
    {
        return lhs.amount;
    }
}
