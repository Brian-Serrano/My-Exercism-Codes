import Foundation

func sliceSize(diameter: Double?, slices: Int?) -> Double? {
  guard let new_diameter: Double = diameter, let new_slices: Int = slices, new_diameter >= 0, new_slices > 0 else { return nil }
  return (pow(new_diameter / 2, 2) * Double.pi) / Double(new_slices)
}

func biggestSlice(diameterA: String, slicesA: String, diameterB: String, slicesB: String) -> String {
  let first: Double? = sliceSize(diameter: Double(diameterA), slices: Int(slicesA))
  let second: Double? = sliceSize(diameter: Double(diameterB), slices: Int(slicesB))
  guard let new_first: Double = first, let new_second: Double = second else {
    if let _: Double = first { return "Slice A is bigger" }
    if let _: Double = second { return "Slice B is bigger" }
    return "Neither slice is bigger"
  }
  return new_first > new_second ? "Slice A is bigger" : (new_first == new_second ? "Neither slice is bigger" : "Slice B is bigger")
}
