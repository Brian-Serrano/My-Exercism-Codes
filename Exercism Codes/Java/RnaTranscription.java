class RnaTranscription {

    String transcribe(String dnaStrand) {
        String[] dnaStrandSplit = dnaStrand.split("");
        String transcribed = "";
        for(int i=0; i<dnaStrandSplit.length; i++) {
            switch(dnaStrandSplit[i]) {
                case "G":
                    transcribed += 'C';
                    break;
                case "C":
                    transcribed += 'G';
                    break;
                case "T":
                    transcribed += 'A';
                    break;
                case "A":
                    transcribed += 'U';
                    break;
            }
        }
        return transcribed;
    }

}