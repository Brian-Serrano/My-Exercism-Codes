import java.util.*;
import java.util.stream.*;
public class Robot {
    private static final Set<String> registeredNames = new HashSet<>();
    private final Random random = new Random();
    private String name;
    
    public Robot() {
        reset();
    }
    
    public String getName() {
        return name;
    }
    
    public void reset() {
        String name;
        do {
            name = generateName();
        }
        while (registeredNames.contains(name));
        registeredNames.add(name);
        this.name = name;
    }
    
    private String randomName(char start, char end, int length) {
        return IntStream.range(0, length)
            .mapToObj(x -> String.valueOf((char)random.nextInt(start, end + 1)))
            .collect(Collectors.joining());
    }
    
    private String generateName() {
        return randomName('A', 'Z', 2) + randomName('0', '9', 3);
    }
}