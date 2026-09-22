import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class VariableLengthQuantity {

    List<String> encode(List<Long> numbers) {
        List<String> res = new ArrayList<>();
        numbers.forEach(n -> {
            StringBuilder binSb = new StringBuilder(Long.toBinaryString(n));
            fillZeros(binSb, 7);
            for(int i = 0; i < binSb.length() / 7; i++) {
                String first = i == binSb.length() / 7 - 1 ? "0" : "1";
                String binStr = first + binSb.substring(i * 7, (i + 1) * 7);
                res.add("0x" + Long.toHexString(Long.parseLong(binStr, 2)));
            }
        });
        return res;
    }

    List<String> decode(List<Long> bytes) {
        StringBuilder sb = new StringBuilder();
        List<String> res = new ArrayList<>();
        for(int i = 0; i < bytes.size(); i++) {
            StringBuilder binSb = new StringBuilder(Long.toBinaryString(bytes.get(i)));
            fillZeros(binSb, 8);
            if(i == bytes.size() - 1 && binSb.charAt(0) == '1') throw new IllegalArgumentException("Invalid variable-length quantity encoding");
            if(binSb.charAt(0) == '0') {
                sb.append(binSb.substring(1));
                res.add("0x" + Long.toHexString(Long.parseLong(sb.toString(), 2)));
                sb.delete(0, sb.length());
            }
            else sb.append(binSb.substring(1));
        }
        return res;
    }

    void fillZeros(StringBuilder sb, int count) {
        if(sb.length() % count != 0) {
            int numOfZ = count - (sb.length() % count);
            for(int i = 0; i < numOfZ; i++) {
                sb.insert(0, "0");
            }
        }
    }
}
