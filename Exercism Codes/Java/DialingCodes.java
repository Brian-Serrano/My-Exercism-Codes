import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;

public class DialingCodes {

    private Map<Integer, String> entries;

    public DialingCodes() {
        entries = new HashMap<>();
    }

    public Map<Integer, String> getCodes() {
        return entries;
    }

    public void setDialingCode(Integer code, String country) {
        entries.put(code, country);
    }

    public String getCountry(Integer code) {
        return entries.get(code);
    }

    public void addNewDialingCode(Integer code, String country) {
        if (!entries.containsKey(code) && !entries.containsValue(country)) {
            setDialingCode(code, country);
        }
    }

    public Integer findDialingCode(String country) {
        for (Entry<Integer, String> entry : entries.entrySet()) {
            if (entry.getValue().equals(country)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void updateCountryDialingCode(Integer code, String country) {
        Integer c = findDialingCode(country);
        if (c != null) {
            entries.remove(c);
            setDialingCode(code, country);
        }
    }
}