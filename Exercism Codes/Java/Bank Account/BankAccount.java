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
    public void open() {
        open = true;
    }
    public void close() {
        open = false;
    }
    public synchronized void deposit(int amount) throws BankAccountActionInvalidException {
        if(amount < 0) {
            throw new BankAccountActionInvalidException("Cannot deposit or withdraw negative amount");
        }
        if(open) {
            balance += amount;
        } else {
            throw new BankAccountActionInvalidException("Account closed");
        }
    }
    public synchronized void withdraw(int amount) throws BankAccountActionInvalidException {
        if(balance == 0) {
            throw new BankAccountActionInvalidException("Cannot withdraw money from an empty account");
        }
        if(amount > balance) {
            throw new BankAccountActionInvalidException("Cannot withdraw more money than is currently in the account");
        }
        if(amount < 0) {
            throw new BankAccountActionInvalidException("Cannot deposit or withdraw negative amount");
        }
        if(open) {
            balance -= amount;
        } else {
            throw new BankAccountActionInvalidException("Account closed");
        }
    }
}