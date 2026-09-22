func canIBuy(vehicle: String, price: Double, monthlyBudget: Double) -> String {
  if(price / 60 <= monthlyBudget) { return "Yes! I'm getting a " + vehicle }
  if(price / 60 <= monthlyBudget * 1.1) { return "I'll have to be frugal if I want a " + vehicle }
  return "Darn! No " + vehicle + " for me"
}

func licenseType(numberOfWheels wheels: Int) -> String {
  switch wheels {
    case 2, 3:
      return "You will need a motorcycle license for your vehicle"
    case 4, 6:
      return "You will need an automobile license for your vehicle"
    case 18:
      return "You will need a commercial trucking license for your vehicle"
    default:
      return "We do not issue licenses for those types of vehicles"
  }
}

func registrationFee(msrp: Int, yearsOld: Int) -> Int {
  if(yearsOld >= 10) { return 25 }
  return Int((Double(max(msrp, 25000)) * (1.0 - (0.1 * Double(yearsOld))) / 100).rounded(.down))
}
