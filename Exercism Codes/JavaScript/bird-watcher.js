export function totalBirdCount(birdsPerDay) {
  let total = 0;
  for (let i = 0; i < birdsPerDay.length; i++) {
    total += birdsPerDay[i];
  }
  return total;
}
export function birdsInWeek(birdsPerDay, week) {
  let total = 0;
  for (let i = (week * 7) - 7; i < week * 7; i++) {
    total += birdsPerDay[i];
  }
  return total;
}
export function fixBirdCountLog(birdsPerDay) {
  for (let i = 0; i < birdsPerDay.length; i++) {
    if (i % 2 === 0) {
      birdsPerDay[i]++;
    }
  }
  return birdsPerDay;
}
