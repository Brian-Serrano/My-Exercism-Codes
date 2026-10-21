using System.Collections.Immutable;
using System.Reactive.Linq;

public class GameState
{
    public string MaskedWord { get; }
    public ImmutableHashSet<char> GuessedChars { get; }
    public int RemainingGuesses { get; }

    public GameState(string maskedWord, ImmutableHashSet<char> guessedChars, int remainingGuesses)
    {
        MaskedWord = maskedWord;
        GuessedChars = guessedChars;
        RemainingGuesses = remainingGuesses;
    }
}

public class TooManyGuessesException : Exception
{
}

public class SaveTheCow
{
    public IObservable<GameState> StateObservable { get; set; }
    public IObserver<char> GuessObserver { get; set; }
  
    public SaveTheCow(string word)
    {
        GameState gameState = new GameState(
            string.Join("", Enumerable.Repeat("_", word.Length)),
            new HashSet<char>().ToImmutableHashSet(),
            9);

        var observable = new StateObservable(gameState);
        StateObservable = observable;
        GuessObserver = new GuessObserver(gameState, word, observable);
    }
}

public class StateObservable : IObservable<GameState>
{
    private GameState state;
    private List<IObserver<GameState>> observers;
    private bool isCompleted = false;

    public StateObservable(GameState state)
    {
        this.state = state;
        observers = new List<IObserver<GameState>>();
    }

    public IDisposable Subscribe(IObserver<GameState> observer)
    {
        if (isCompleted)
        {
            observer.OnCompleted();
            return new Unsubscriber(() => { });
        }

        observers.Add(observer);
        observer.OnNext(state);

        return new Unsubscriber(() => observers.Remove(observer));
    }

    public void Publish(GameState state)
    {
        this.state = state;

        foreach (var o in observers)
        {
            o.OnNext(state);
        }
    }

    public void PublishError(GameState state, TooManyGuessesException error)
    {
        this.state = state;

        foreach (var o in observers)
        {
            o.OnError(error);
        }

        observers.Clear();
    }

    public void PublishComplete(GameState state)
    {
        if (isCompleted)
            return;

        this.state = state;
        isCompleted = true;

        foreach (var o in observers)
        {
            o.OnCompleted();
        }

        observers.Clear();
    }

    private sealed class Unsubscriber(Action unsubscribe) : IDisposable
    {
        public void Dispose() => unsubscribe();
    }
}

public class GuessObserver : IObserver<char>
{
    private GameState state;
    private string word;
    private StateObservable observable;

    public GuessObserver(GameState state, string word, StateObservable observable)
    {
        this.state = state;
        this.word = word;
        this.observable = observable;
    }

    public void OnCompleted()
    {
        throw new NotImplementedException();
    }

    public void OnError(Exception error)
    {
        throw error;
    }

    public void OnNext(char value)
    {
        if (word.Contains(char.ToLower(value)) && !state.GuessedChars.Contains(char.ToLower(value)))
        {
            state = new GameState(
                string.Join("", state.MaskedWord.Select((w, i) => char.ToLower(value) == word[i] ? char.ToLower(value) : w)),
                state.GuessedChars.Add(char.ToLower(value)),
                state.RemainingGuesses);

            if (!state.MaskedWord.Contains('_'))
            {
                observable.PublishComplete(state);
                return;
            }
        }
        else
        {
            if (state.RemainingGuesses <= 0)
            {
                observable.PublishError(state, new TooManyGuessesException());
                return;
            }

            state = new GameState(
                state.MaskedWord,
                state.GuessedChars.Add(char.ToLower(value)),
                state.RemainingGuesses - 1);
        }
        observable.Publish(state);
    }
}
