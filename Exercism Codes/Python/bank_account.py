class BankAccount:
    def __init__(self):
        self.op = False
        self.balance = 0

    def get_balance(self):
        if not self.op:
            raise ValueError("account not open")

        return self.balance

    def open(self):
        if self.op:
            raise ValueError("account already open")
        if self.balance > 0:
            self.balance = 0

        self.op = True

    def deposit(self, amount):
        if amount < 0:
            raise ValueError("amount must be greater than 0")
        if not self.op:
            raise ValueError("account not open")

        self.balance += amount

    def withdraw(self, amount):
        if not self.op:
            raise ValueError("account not open")
        if amount > self.balance:
            raise ValueError("amount must be less than balance")
        if amount < 0:
            raise ValueError("amount must be greater than 0")

        self.balance -= amount

    def close(self):
        if not self.op:
            raise ValueError("account not open")

        self.op = False
