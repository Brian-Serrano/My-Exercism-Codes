class BankAccount {
    private boolean open = false;
    private int balance = 0;

    public int getBalance() throws BankAccountActionInvalidException {
        if(open) {
            return balance;
        } else {
            throw new BankAccountActionInvalidException("Account closed");
        }
    }
    public void open() throws BankAccountActionInvalidException {
        if (open) {
            throw new BankAccountActionInvalidException("Account already open");
        }
        if (balance > 0) {
            balance = 0;
        }

        open = true;
    }
    public void close() throws BankAccountActionInvalidException {
        if (!open) {
            throw new BankAccountActionInvalidException("Account not open");
        }

        open = false;
    }
    public synchronized void deposit(int amount) throws BankAccountActionInvalidException {
        if(!open) {
            throw new BankAccountActionInvalidException("Account closed");
        }
        if(amount < 0) {
            throw new BankAccountActionInvalidException("Cannot deposit or withdraw negative amount");
        }

        balance += amount;
    }
    public synchronized void withdraw(int amount) throws BankAccountActionInvalidException {
        if(!open) {
            throw new BankAccountActionInvalidException("Account closed");
        }
        if(balance == 0) {
            throw new BankAccountActionInvalidException("Cannot withdraw money from an empty account");
        }
        if(amount > balance) {
            throw new BankAccountActionInvalidException("Cannot withdraw more money than is currently in the account");
        }
        if(amount < 0) {
            throw new BankAccountActionInvalidException("Cannot deposit or withdraw negative amount");
        }

        balance -= amount;
    }
}
