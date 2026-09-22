export class BankAccount {
    constructor() {
        this.status = false;
        this.bal = 0;
    }
    open() {
        if(!this.status)
            this.status = true;
        else
            throw new ValueError();
    }
    close() {
        if(this.status) {
            this.status = false;
            this.bal = 0;
        }
    else
        throw new ValueError();
    }
    deposit(b) {
        if(this.status && b >= 0)
            this.bal += b;
        else
            throw new ValueError();
    }
    withdraw(b) {
        if(this.status && b >= 0 && this.bal - b >= 0)
            this.bal -= b;
        else
            throw new ValueError();
    }
    get balance() {
        if(this.status)
            return this.bal;
        else
            throw new ValueError();
    }
}
export class ValueError extends Error {
    constructor() {
        super('Bank account error');
    }
}
