import json


class RestAPI:
    def __init__(self, database=None):
        self.user_json = database["users"] if database else []

    def get(self, url, payload=None):
        if url == "/users":
            result = {"users": []}

            if payload is None:
                for user in self.user_json:
                    result["users"].append(user)
            else:
                names = json.loads(payload)["users"]
                for user in self.user_json:
                    if user["name"] in names:
                        result["users"].append(user)

            return json.dumps(result)

        return ""

    def post(self, url, payload=None):
        if url == "/add":
            user = {
                "name": json.loads(payload)["user"],
                "owes": {},
                "owed_by": {},
                "balance": 0.0,
            }
            self.user_json.append(user)
            return json.dumps(user)
        if url == "/iou":
            json_array = []
            p = json.loads(payload)
            lender = p["lender"]
            borrower = p["borrower"]
            amount = p["amount"]
            for user in self.user_json:
                name = user["name"]
                if borrower == name:
                    owes = user["owes"]
                    owed_by = user["owed_by"]

                    balance = (owed_by[lender] if lender in owed_by else 0) - amount
                    if balance == 0:
                        owes.pop(lender, None)
                        owed_by.pop(lender, None)
                    if balance < 0:
                        owes[lender] = -balance
                        owed_by.pop(lender, None)
                    if balance > 0:
                        owed_by[lender] = balance

                    user["balance"] = user["balance"] - amount
                    json_array.append(user)
                if lender == name:
                    owes = user["owes"]
                    owed_by = user["owed_by"]

                    balance = (owes[borrower] if borrower in owes else 0) - amount
                    if balance == 0:
                        owes.pop(borrower, None)
                        owed_by.pop(borrower, None)
                    if balance < 0:
                        owed_by[borrower] = -balance
                        owes.pop(borrower, None)
                    if balance > 0:
                        owes[borrower] = balance

                    user["balance"] = user["balance"] + amount
                    json_array.append(user)

            return json.dumps({"users": json_array})

        return ""