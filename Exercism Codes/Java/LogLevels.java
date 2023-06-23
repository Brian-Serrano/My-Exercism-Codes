public class LogLevels {
    
    public static String message(String logLine) {
        String[] arr = logLine.split(":", 2);
        return arr[1].trim();
    }

    public static String logLevel(String logLine) {
        String[] arr = logLine.split(":", 2);
        return arr[0].replace("[", "").replace("]", "").toLowerCase();
    }

    public static String reformat(String logLine) {
        return message(logLine) + " (" + logLevel(logLine) + ")";
    }
}