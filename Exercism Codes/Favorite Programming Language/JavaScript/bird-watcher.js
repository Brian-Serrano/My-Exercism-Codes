export function totalBirdCount(birdsPerDay) {
    return birdsPerDay.reduce((accumulator, currentValue) => accumulator + currentValue, 0);
  }
  export function birdsInWeek(birdsPerDay, week) {
    birdsPerDay = birdsPerDay.slice((week*7)-7, week*7);
    return birdsPerDay.reduce((accumulator, currentValue) => accumulator + currentValue, 0);
  }
  export function fixBirdCountLog(birdsPerDay) {
    for (let i = 0; i < birdsPerDay.length; i++) {
      if (i % 2 === 0) {
        birdsPerDay[i]++;
      }
    }
    return birdsPerDay;
  }
  