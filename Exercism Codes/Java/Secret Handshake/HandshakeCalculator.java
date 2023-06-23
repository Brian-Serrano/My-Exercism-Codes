import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class HandshakeCalculator {

    List<Signal> calculateHandshake(int number) {
        String binary = Integer.toBinaryString(number);
        String[] arr = binary.split("");
        int j = 0;
        List<Signal> signals = new ArrayList<Signal>();
        for(int i=arr.length-1; i>=0; i--){
            if(arr[i].equals("1")){
                switch(j){
                    case 0:
                        signals.add(Signal.WINK);
                        break;
                    case 1:
                        signals.add(Signal.DOUBLE_BLINK);
                        break;
                    case 2:
                        signals.add(Signal.CLOSE_YOUR_EYES);
                        break;
                    case 3:
                        signals.add(Signal.JUMP);
                        break;
                    case 4:
                        Collections.reverse(signals);
                        break;
                }
            }
            j++;
        }
        return signals;
    }

}