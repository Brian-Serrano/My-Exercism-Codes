object SpaceAge {
  def onEarth(age: Double): Double = age / (1.0 * 31557600)

  def onVenus(age: Double): Double = age / (0.61519726 * 31557600)

  def onMercury(age: Double): Double = age / (0.2408467 * 31557600)

  def onMars(age: Double): Double = age / (1.8808158 * 31557600)

  def onJupiter(age: Double): Double = age / (11.862615 * 31557600)

  def onSaturn(age: Double): Double = age / (29.447498 * 31557600)

  def onUranus(age: Double): Double = age / (84.016846 * 31557600)

  def onNeptune(age: Double): Double = age / (164.79132 * 31557600)
}
