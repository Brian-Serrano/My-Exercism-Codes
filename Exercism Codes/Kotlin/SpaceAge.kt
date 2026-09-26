class SpaceAge {
    private var seconds: Double
    constructor(sec: Int) {
        seconds = sec / 31557600.0
    }
    fun onEarth(): Double = seconds / 1.0
    fun onMercury(): Double = seconds / 0.2408467
    fun onVenus(): Double = seconds / 0.61519726
    fun onMars(): Double = seconds / 1.8808158
    fun onJupiter(): Double = seconds / 11.862615
    fun onSaturn(): Double = seconds / 29.447498
    fun onUranus(): Double = seconds / 84.016846
    fun onNeptune(): Double = seconds / 164.79132
}