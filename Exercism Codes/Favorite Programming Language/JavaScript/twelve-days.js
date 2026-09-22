//
// This is only a SKELETON file for the 'Twelve Days' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

const phrases = [
  "a Partridge in a Pear Tree",
  "two Turtle Doves",
  "three French Hens",
  "four Calling Birds",
  "five Gold Rings",
  "six Geese-a-Laying",
  "seven Swans-a-Swimming",
  "eight Maids-a-Milking",
  "nine Ladies Dancing",
  "ten Lords-a-Leaping",
  "eleven Pipers Piping",
  "twelve Drummers Drumming"
];

const number = [
  "first", "second", "third",
  "fourth", "fifth", "sixth",
  "seventh", "eighth", "ninth",
  "tenth", "eleventh", "twelfth"
]

export const recite = (...args) => {
  if (args.length == 2) {
    let verse = "";
    for (let i = args[0]; i <= args[1]; i++) {
      const seperator = i == args[1] ? "" : "\n";
      verse += generateVerse(i) + seperator;
    }
    return verse;
  }
  if (args.length == 1) {
    return generateVerse(args[0]);
  }
};

const generateVerse = (verseNumber) => {
  let verse = "On the " + number[verseNumber - 1] + " day of Christmas my true love gave to me: ";
  for (let i = verseNumber - 1; i >= 0; i--) {
    const seperator = i == 0 ? "" : ", ";
    if (i == 0 && verseNumber > 1) {
      verse += "and ";
    }
    verse += phrases[i] + seperator;
  }
  verse += ".\n";
  return verse;
};