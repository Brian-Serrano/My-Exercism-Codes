object EliudsEggs {

    fun eggCount(number: Int): Int{
        return Integer.toBinaryString(number).count { it == '1' }
    }
}
