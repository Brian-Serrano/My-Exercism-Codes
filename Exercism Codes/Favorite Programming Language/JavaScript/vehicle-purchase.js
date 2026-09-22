export function needsLicense(kind) {
  return kind === 'car' || kind === 'truck';
}
export function chooseVehicle(option1, option2) {
  let options = [option1, option2];
  options.sort();
  return options[0] + " is clearly the better choice.";
}
export function calculateResellPrice(originalPrice, age) {
  if(age < 3) {
    originalPrice *= 0.80;
  }
  if(age >= 3 && age <= 10) {
    originalPrice *= 0.70;
  }
  if(age > 10) {
    originalPrice *= 0.50;
  }
  return originalPrice;
}