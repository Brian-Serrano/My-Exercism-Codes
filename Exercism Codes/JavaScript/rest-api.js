//
// This is only a SKELETON file for the 'Rest API' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export class RestAPI {
  constructor(database) {
    this.userJson = database.users;
  }

  get(url) {
    const query = url.match(/\?.+/);
    if (query == null) {
      return { users: this.userJson };
    }
    else {
      const newUrl = url.substring(0, url.length - query[0].length);
      const params = new URLSearchParams(query[0]);
      const result = [];
      const names = params.getAll("users");
      for (const user of this.userJson) {
        if (names.includes(user.name)) {
          result.push(user);
        }
      }
      return { users: result };
    }
    return "";
  }

  post(url, payload) {
    if (url == "/add") {
      const user = {
        name: payload.user,
        owes: {},
        owed_by: {},
        balance: 0
      }
      this.userJson.push(user);
      return user;
    }
    if (url == "/iou") {
      const jsonArray = [];
      const lender = payload.lender;
      const borrower = payload.borrower;
      const amount = payload.amount;
      for (const user of this.userJson) {
        const name = user.name;
        if (borrower == name) {
          const owes = user.owes;
          const owedBy = user.owed_by;

          const balance = (lender in owedBy ? owedBy[lender] : 0) - amount;
          if (balance == 0) {
            delete owes[lender];
            delete owedBy[lender];
          }
          if (balance < 0) {
            owes[lender] = -balance;
            delete owedBy[lender];
          }
          if (balance > 0) {
            owedBy[lender] = balance;
          }
          user.balance = user.balance - amount;
          jsonArray.push(user);
        }
        if (lender == name) {
          const owes = user.owes;
          const owedBy = user.owed_by;

          const balance = (borrower in owes ? owes[borrower] : 0) - amount;
          if (balance == 0) {
            delete owedBy[borrower];
            delete owes[borrower];
          }
          if (balance < 0) {
            owedBy[borrower] = -balance;
            delete owes[borrower];
          }
          if (balance > 0) {
            owes[borrower] = balance;
          }
          user.balance = user.balance + amount;
          jsonArray.push(user);
        }
      }
      return { users: jsonArray };
    }
    return "";
  }
}
