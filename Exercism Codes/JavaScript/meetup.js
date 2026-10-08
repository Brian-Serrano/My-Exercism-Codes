//
// This is only a SKELETON file for the 'Meetup' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

const days = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];

export const meetup = (year, month, schedule, dayOfWeek) => {
  const lastDay = new Date(year, month, 0).getDate();
  if (schedule == "first")
    return findDay(year, month, 1, 7, dayOfWeek);
  else if (schedule == "second")
    return findDay(year, month, 8, 14, dayOfWeek);
  else if (schedule == "third")
    return findDay(year, month, 15, 21, dayOfWeek);
  else if (schedule == "fourth")
    return findDay(year, month, 22, 28, dayOfWeek);
  else if (schedule == "last")
    return findDay(year, month, lastDay - 6, lastDay, dayOfWeek);
  else if (schedule == "teenth")
    return findDay(year, month, 13, 19, dayOfWeek);
  else
    return findDay(year, month, 1, 7, dayOfWeek);
};

const findDay = (year, month, start, end, dayOfWeek) => {
  let day = null;
  for (let n = start; n <= end; n++) {
    if (days[new Date(year, month - 1, n).getDay()] == dayOfWeek) {
      day = n;
    }
  }
  return new Date(year, month - 1, day);
};
