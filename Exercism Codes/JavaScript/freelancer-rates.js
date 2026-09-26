export function dayRate(ratePerHour) {
  return ratePerHour * 8;
}

export function daysInBudget(budget, ratePerHour) {
  return Math.floor(budget / dayRate(ratePerHour));
}

export function priceWithMonthlyDiscount(ratePerHour, numDays, discount) {
  var remainingDays = numDays % 22;
  return Math.ceil((remainingDays * dayRate(ratePerHour)) + (dayRate(ratePerHour) * (numDays - remainingDays) * (1 - discount)));
}