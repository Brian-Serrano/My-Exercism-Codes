public class BankAccount
{
    private bool open;
    private decimal balance;
    private readonly object _lock = new();

    public void Open()
    {
        if (open)
        {
            throw new InvalidOperationException("account already open");
        }
        if (balance > 0m)
        {
            balance = 0m;
        }

        open = true;
    }

    public void Close()
    {
        if (!open)
        {
            throw new InvalidOperationException("account not open");
        }

        open = false;
    }

    public decimal Balance
    {
        get
        {
            if (!open)
            {
                throw new InvalidOperationException("account not open");
            }

            return balance;
        }
    }

    public void Deposit(decimal change)
    {
        lock (_lock)
        {
            if (change < 0m)
            {
                throw new InvalidOperationException("amount must be greater than 0");
            }
            if (!open)
            {
                throw new InvalidOperationException("account not open");
            }

            balance += change;
        }
    }

    public void Withdraw(decimal change)
    {
        lock (_lock)
        {
            if (!open)
            {
                throw new InvalidOperationException("account not open");
            }
            if (change > balance)
            {
                throw new InvalidOperationException("amount must be less than balance");
            }
            if (change < 0m)
            {
                throw new InvalidOperationException("amount must be greater than 0");
            }

            balance -= change;
        }
    }
}
