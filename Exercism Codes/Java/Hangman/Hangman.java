import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import java.util.*;

class Hangman {

    Observable<Output> play(Observable<String> secret, Observable<String> letters) {
        List<String> let = new ArrayList<>();
        Disposable disposableLetter = letters.subscribe(let::add);
        List<String> sec = new ArrayList<>();
        Disposable disposableSecret = secret.subscribe(sec::add);

        List<Output> output = new ArrayList<>();

        Set<String> guess = new HashSet<>();
        Set<String> misses = new HashSet<>();
        List<Part> parts = new ArrayList<>();
        List<String> letter = new ArrayList<>(let);

        String currWord = sec.remove(0);
        String[] discovered = "_".repeat(currWord.length()).split("");
        Status status = Status.PLAYING;

        while(!letter.isEmpty()) {
            status = Status.PLAYING;
            String currLetter = letter.remove(0);
            if(guess.contains(currLetter) || misses.contains(currLetter)) {
                return Observable.error(new IllegalArgumentException("Letter " + currLetter + " was already played"));
            }
            if (currWord.contains(currLetter)) {
                guess.add(currLetter);
                for (int i = currWord.indexOf(currLetter); i >= 0; i = currWord.indexOf(currLetter, i + 1)) {
                    discovered[i] = currLetter;
                }
            }
            else {
                misses.add(currLetter);
                parts.add(Part.values()[misses.size() - 1]);
            }
            if(parts.size() == Part.values().length) {
                status = Status.LOSS;
                output.add(new Output(currWord, String.join("", discovered), guess, misses, parts, status));
                guess = new HashSet<>();
                misses = new HashSet<>();
                parts = new ArrayList<>();
                if(sec.isEmpty()) break;
                currWord = sec.remove(0);
                discovered = "_".repeat(currWord.length()).split("");
            }
            if(Arrays.stream(discovered).noneMatch(d -> d.equals("_"))) {
                status = Status.WIN;
                output.add(new Output(currWord, String.join("", discovered), guess, misses, parts, status));
                guess = new HashSet<>();
                misses = new HashSet<>();
                parts = new ArrayList<>();
                if(sec.isEmpty()) break;
                currWord = sec.remove(0);
                discovered = "_".repeat(currWord.length()).split("");
            }
        }

        if((!misses.isEmpty() || !guess.isEmpty()) || let.isEmpty()) {
            output.add(new Output(currWord, String.join("", discovered), guess, misses, parts, status));
        }

        return Observable.fromIterable(output);
    }

}