class School {
  private var mp: Map[Int, Seq[String]] = Map()

  def add(name: String, g: Int): Unit = mp = mp.updated(g, mp.getOrElse(g, Seq()) :+ name)

  def db: Map[Int, Seq[String]] = mp

  def grade(g: Int): Seq[String] = mp.getOrElse(g, Seq())

  def sorted: Map[Int, Seq[String]] = Map(mp.view.mapValues(_.sorted).toSeq.sortBy(_._1):_*)
}

