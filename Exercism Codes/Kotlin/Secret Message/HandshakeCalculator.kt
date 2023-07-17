object HandshakeCalculator {
    fun calculateHandshake(number: Int): List<Signal> {
        var myList : MutableList<Signal> = mutableListOf()
        if(number > 31 || number < 1) return myList
        var signals : List<Signal> = listOf(Signal.WINK, Signal.DOUBLE_BLINK, Signal.CLOSE_YOUR_EYES, Signal.JUMP)
        val bin : String = number.toString(2).reversed()
        for(i in bin.indices) {
            if(bin[i] == '1') if(i != 4) myList.add(signals[i]) else myList = myList.reversed().toMutableList()
        }

        return myList
    }
}
