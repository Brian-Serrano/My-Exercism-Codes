//
// This is only a SKELETON file for the 'House' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

const phrases = [
  " the house that Jack built.",
  " the malt",
  " the rat",
  " the cat",
  " the dog",
  " the cow with the crumpled horn",
  " the maiden all forlorn",
  " the man all tattered and torn",
  " the priest all shaven and shorn",
  " the rooster that crowed in the morn",
  " the farmer sowing his corn",
  " the horse and the hound and the horn"
];

const words = [
  "lay in", "ate", "killed", "worried", "tossed", "milked", "kissed", "married", "woke", "kept", "belonged to"
];

export class House {
  static verse(verseNumber) {
    let phraseGroup = [];
    for (let i = verseNumber - 1; i >= 0; i--)
      phraseGroup.push((i == verseNumber - 1 ? "This is" : "that " + words[i]) + phrases[i]);
    return phraseGroup;
  }

  static verses(start, end) {
    let phraseGroups = [];
    for (let i = start; i <= end; i++){
      phraseGroups = [...phraseGroups, ...this.verse(i)];
      if (i != end)
        phraseGroups.push("");
    }
    return phraseGroups;
  }
}
