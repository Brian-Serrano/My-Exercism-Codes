export function isValidCommand(command) {
  return /^chatbot/gi.test(command);
}

export function removeEmoji(message) {
  return message.replace(/emoji([0-9])+/g, "");
}

export function checkPhoneNumber(number) {
  return /\(\+\d{2}\) \d{3}-\d{3}-\d{3}/g.test(number) ? 'Thanks! You can now download me to your phone.' : "Oops, it seems like I can't reach out to " + number;
}

export function getURL(userInput) {
  return userInput.match(/\w+\.\w+/g);
}

export function niceToMeetYou(fullName) {
  return fullName.replace(/(\w+), (\w+)/g, (match, p1, p2) => `Nice to meet you, ${p2} ${p1}`);
}
