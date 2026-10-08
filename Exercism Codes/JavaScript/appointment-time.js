export function createAppointment(days, now = undefined) {
  const date = now === undefined ? new Date() : new Date(now);
  date.setDate(date.getDate() + days);
  return date;
}

export function getAppointmentTimestamp(appointmentDate) {
  return appointmentDate.toISOString();
}

export function getAppointmentDetails(timestamp) {
  const date = new Date(timestamp);
  return {
    year: date.getFullYear(),
    month: date.getMonth(),
    date: date.getDate(),
    hour: date.getHours(),
    minute: date.getMinutes()
  };
}

export function updateAppointment(timestamp, options) {
  const date = new Date(timestamp);
  for (const [key, value] of Object.entries(options)) {
    switch (key) {
      case "year":
        date.setFullYear(value);
        break;
      case "month":
        date.setMonth(value);
        break;
      case "date":
        date.setDate(value);
        break;
      case "hour":
        date.setHours(value);
        break;
      case "minute":
        date.setMinutes(value);
        break;
    }
  }
  return {
    year: date.getFullYear(),
    month: date.getMonth(),
    date: date.getDate(),
    hour: date.getHours(),
    minute: date.getMinutes()
  };
}

export function timeBetween(timestampA, timestampB) {
  const a = new Date(timestampA);
  const b = new Date(timestampB);
  return Math.round((b.getTime() - a.getTime()) / 1000);
}

export function isValid(appointmentTimestamp, currentTimestamp) {
  return timeBetween(currentTimestamp, appointmentTimestamp) > 0;
}
