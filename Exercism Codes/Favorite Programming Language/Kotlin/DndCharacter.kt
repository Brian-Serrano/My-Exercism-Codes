import kotlin.random.Random

class DndCharacter {

    val strength: Int = ability()
    val dexterity: Int = ability()
    val constitution: Int = ability()
    val intelligence: Int = ability()
    val wisdom: Int = ability()
    val charisma: Int = ability()
    val hitpoints: Int = 10 + modifier(constitution)

    companion object {

        fun ability(): Int {
            return List(4) { Random.nextInt(1, 7) }.sorted().drop(1).sum()
        }

        fun modifier(score: Int): Int {
            return Math.floorDiv(score - 10, 2)
        }
    }

}