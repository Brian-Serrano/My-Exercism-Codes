import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SplitSecondStopwatch {
    private String state;
    private LocalTime total;
    private LocalTime currentLap;
    private List<LocalTime> previousLaps;
    private final DateTimeFormatter formatter;

    public SplitSecondStopwatch() {
        state = "ready";
        total = LocalTime.MIN;
        currentLap = LocalTime.MIN;
        previousLaps = new ArrayList<>();
        formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    }

    public void start() {
        if (state.equals("running"))
            throw new IllegalStateException("cannot start an already running stopwatch");

        state = "running";
    }

    public void stop() {
        if (!state.equals("running"))
            throw new IllegalStateException("cannot stop a stopwatch that is not running");

        state = "stopped";
    }

    public void reset() {
        if (!state.equals("stopped"))
            throw new IllegalStateException("cannot reset a stopwatch that is not stopped");

        state = "ready";
        total = LocalTime.MIN;
        currentLap = LocalTime.MIN;
        previousLaps = new ArrayList<>();
    }

    public void lap() {
        if (!state.equals("running"))
            throw new IllegalStateException("cannot lap a stopwatch that is not running");

        previousLaps.add(currentLap);
        currentLap = LocalTime.MIN;
    }

    public String state() {
        return state;
    }

    public String currentLap() {
        return currentLap.format(formatter);
    }

    public String total() {
        return total.format(formatter);
    }

    public List<String> previousLaps() {
        return previousLaps.stream().map(l -> l.format(formatter)).toList();
    }

    public void advanceTime(String timeString) {
        if (state.equals("running")) {
            LocalTime timeToAdd = LocalTime.parse(timeString);

            total = total
                    .plusHours(timeToAdd.getHour())
                    .plusMinutes(timeToAdd.getMinute())
                    .plusSeconds(timeToAdd.getSecond());

            currentLap = currentLap
                    .plusHours(timeToAdd.getHour())
                    .plusMinutes(timeToAdd.getMinute())
                    .plusSeconds(timeToAdd.getSecond());
        }
    }
}