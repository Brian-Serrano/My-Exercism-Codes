//
// This is only a SKELETON file for the 'Bob' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const hey = (message) => {
  const heyBob = message.trim();
  const endsWithQM = heyBob[heyBob.length - 1] == "?";
  const isUpper = heyBob == heyBob.toUpperCase();
  const containsLetter = (/[a-z]/i).test(heyBob)
  if (heyBob.length == 0)
    return "Fine. Be that way!";
  if (endsWithQM && isUpper && containsLetter)
    return "Calm down, I know what I'm doing!";
  if (endsWithQM)
    return "Sure.";
  if (isUpper && containsLetter)
    return "Whoa, chill out!";

  return "Whatever.";
};
