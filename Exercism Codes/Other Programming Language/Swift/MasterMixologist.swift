let dict: [String: Double] = ["beer": 0.5, "soda": 0.5, "water": 0.5, "shot": 1.0, "mixed drink": 1.5, "fancy drink": 2.5, "frozen drink": 3.0]
let wedge: [String: Int] = ["small": 6, "medium": 8, "large": 10]
func timeToPrepare(drinks: [String]) -> Double {
  return drinks.reduce(0, {  (acc: Double, next: String) in acc + dict[next]! })
}

func makeWedges(needed: Int, limes: [String]) -> Int {
  return limes.reduce([0, 0], { (acc: [Int], next: String) in acc[1] < needed ? [acc[0] + 1, acc[1] + wedge[next]!] : acc})[0]
}

func finishShift(minutesLeft: Int, remainingOrders: [[String]]) -> [[String]] {
  var mutableOrders: [[String]] = remainingOrders
  var min: Double = Double(minutesLeft)
  while !(min <= 0 || mutableOrders.isEmpty) {
    min -= timeToPrepare(drinks: mutableOrders.removeFirst())
  }
  return mutableOrders
}

func orderTracker(orders: [(drink: String, time: String)]) -> (
  beer: (first: String, last: String, total: Int)?, soda: (first: String, last: String, total: Int)?
) {
  var beers: [String] = [String]()
  var sodas: [String] = [String]()
  for order: (drink: String, time: String) in orders {
    if order.drink == "beer" {
      beers.append(order.time)
    }
    if order.drink == "soda" {
      sodas.append(order.time)
    }
  }
  return (beers.isEmpty ? nil: (first: beers.first!, last: beers.last!, total: beers.count),
    sodas.isEmpty ? nil: (first: sodas.first!, last: sodas.last!, total: sodas.count)
  )
}
