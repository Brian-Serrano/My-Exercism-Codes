object Hamming {
  def distance(dnaStrandOne: String, dnaStrandTwo: String): Option[Int] = {
    if(dnaStrandOne.length != dnaStrandTwo.length) return None
    Some(Range(0, dnaStrandOne.length).count(i => dnaStrandOne(i) != dnaStrandTwo(i)))
  }
}
