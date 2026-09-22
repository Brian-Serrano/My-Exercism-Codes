export function frontDoorResponse(line) {
  line = line.trim();
  return line[0];
}
export function frontDoorPassword(word) {
  return word.charAt(0).toUpperCase() + word.slice(1).toLowerCase();
}
export function backDoorResponse(line) {
  line = line.trim();
  return line[line.length - 1];
}
export function backDoorPassword(word) {
  return word.charAt(0).toUpperCase() + word.slice(1).toLowerCase() + ", please";
}