import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class RestApi {

    List<JSONObject> userJson = new ArrayList<>();

    String get(String url) {
        if(url.equals("/users")) {
            JSONArray userArr = new JSONArray();
            for(JSONObject u : userJson) userArr.put(u);
            return new JSONObject().put("users", userArr).toString();
        }
        return "";
    }

    String get(String url, JSONObject payload) {
        if(url.equals("/users")) {
            JSONArray userArr = new JSONArray();
            List<Object> names = payload.getJSONArray("users").toList();
            for(JSONObject u : userJson) {
                if(names.contains(u.get("name"))) {
                    userArr.put(u);
                }
            }
            return new JSONObject().put("users", userArr).toString();
        }
        return "";
    }

    String post(String url, JSONObject payload) {
        if(url.equals("/add")) {
            JSONObject user = makeJSON(User.builder().setName((String) payload.get("user")).build());
            userJson.add(user);
            return user.toString();
        }
        if(url.equals("/iou")) {
            JSONArray json = new JSONArray();
            String lender = payload.getString("lender");
            String borrower = payload.getString("borrower");
            double amount = payload.getDouble("amount");
            for(JSONObject u : userJson) {
                String name = u.getString("name");
                if(borrower.equals(name)) {
                    JSONObject owes = u.getJSONObject("owes");
                    JSONObject owedBy = u.getJSONObject("owedBy");

                    double balance = (owedBy.has(lender) ? owedBy.getDouble(lender) : 0) - amount;
                    if(balance == 0) {
                        owes.remove(lender);
                        owedBy.remove(lender);
                    }
                    if(balance < 0) {
                        owes.put(lender, -balance);
                        owedBy.remove(lender);
                    }
                    if(balance > 0) {
                        owedBy.put(lender, balance);
                    }
                    u.put("balance", u.getDouble("balance") - amount);
                    json.put(u);
                }
                if(lender.equals(name)) {
                    JSONObject owes = u.getJSONObject("owes");
                    JSONObject owedBy = u.getJSONObject("owedBy");

                    double balance = (owes.has(borrower) ? owes.getDouble(borrower) : 0) - amount;
                    if(balance == 0) {
                        owedBy.remove(borrower);
                        owes.remove(borrower);
                    }
                    if(balance < 0) {
                        owedBy.put(borrower, -balance);
                        owes.remove(borrower);
                    }
                    if(balance > 0) {
                        owes.put(borrower, balance);
                    }
                    u.put("balance", u.getDouble("balance") + amount);
                    json.put(u);
                }
            }
            return new JSONObject().put("users", json).toString();
        }
        return "";
    }

    RestApi(User... user) {
        for(User u : user) userJson.add(makeJSON(u));
    }

    JSONObject makeJSON(User user) {
        double balance = 0;
        JSONObject owes = new JSONObject();
        JSONObject owedBy = new JSONObject();
        for(Iou o : user.owes()) {
            balance -= o.amount;
            owes.put(o.name, o.amount);
        }
        for(Iou o : user.owedBy()) {
            balance += o.amount;
            owedBy.put(o.name, o.amount);
        }
        return new JSONObject().put("name", user.name()).put("owes", owes).put("owedBy", owedBy).put("balance", balance);
    }

}