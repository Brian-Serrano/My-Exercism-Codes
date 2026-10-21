public enum StopwatchState
{
    Ready,
    Running,
    Stopped
}

public class SplitSecondStopwatch
{
    private TimeProvider time;

    public StopwatchState State { get; private set; } = StopwatchState.Ready;
    public TimeSpan CurrentLap
    {
        get
        {
            if (State == StopwatchState.Stopped)
                if (time.GetTimestamp() > StopTime)
                    LapOffset += time.GetTimestamp() - StopTime;

            return time.GetElapsedTime(LapOffset, time.GetTimestamp());
        }
    }
    public TimeSpan Total
    {
        get
        {
            if (State == StopwatchState.Stopped)
                if (time.GetTimestamp() > StopTime)
                    Offset += time.GetTimestamp() - StopTime;

            return time.GetElapsedTime(Offset, time.GetTimestamp());
        }
    }
    public List<TimeSpan> PreviousLaps { get; private set; } = new List<TimeSpan>();

    public long Offset { get; private set; }
    public long LapOffset { get; private set; }
    public long StopTime { get; private set; }

    public SplitSecondStopwatch(TimeProvider time)
    {
        this.time = time;

        Offset = time.GetTimestamp();
        LapOffset = time.GetTimestamp();
        StopTime = time.GetTimestamp();
    }

    public void Start()
    {
        if (State == StopwatchState.Running)
            throw new InvalidOperationException();

        if (State == StopwatchState.Stopped)
        {
            if (time.GetTimestamp() > StopTime)
            {
                LapOffset += time.GetTimestamp() - StopTime;
                Offset += time.GetTimestamp() - StopTime;
            }
        }

        State = StopwatchState.Running;
    }

    public void Stop()
    {
        if (State != StopwatchState.Running)
            throw new InvalidOperationException();

        State = StopwatchState.Stopped;
        StopTime = time.GetTimestamp();
    }

    public void Reset()
    {
        if (State != StopwatchState.Stopped)
            throw new InvalidOperationException();

        State = StopwatchState.Ready;
        Offset = time.GetTimestamp();
        LapOffset = time.GetTimestamp();
        PreviousLaps = new List<TimeSpan>();
    }

    public void Lap()
    {
        if (State != StopwatchState.Running)
            throw new InvalidOperationException();

        PreviousLaps.Add(CurrentLap);
        LapOffset = time.GetTimestamp();
    }
}
