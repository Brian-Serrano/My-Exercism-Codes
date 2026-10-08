//
// This is only a SKELETON file for the 'Food Chain' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

const phrases = [
  "I know an old lady who swallowed a fly.\n" + "I don't know why she swallowed the fly. Perhaps she'll die.",
  "I know an old lady who swallowed a spider.\n" + "It wriggled and jiggled and tickled inside her.\n",
  "I know an old lady who swallowed a bird.\n" + "How absurd to swallow a bird!\n",
  "I know an old lady who swallowed a cat.\n" + "Imagine that, to swallow a cat!\n",
  "I know an old lady who swallowed a dog.\n" + "What a hog, to swallow a dog!\n",
  "I know an old lady who swallowed a goat.\n" + "Just opened her throat and swallowed a goat!\n",
  "I know an old lady who swallowed a cow.\n" + "I don't know how she swallowed a cow!\n",
  "I know an old lady who swallowed a horse.\n" + "She's dead, of course!"
];

const otherPhrases = [
  "She swallowed the spider to catch the fly.\n" + "I don't know why she swallowed the fly. Perhaps she'll die.",
  "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n",
  "She swallowed the cat to catch the bird.\n",
  "She swallowed the dog to catch the cat.\n",
  "She swallowed the goat to catch the dog.\n",
  "She swallowed the cow to catch the goat.\n"
];

export class Song {
  verse(verseNumber) {
    let phraseGroup = "";
    if (verseNumber > 7)
      return phrases[7] + "\n";
    for (let i = verseNumber - 1; i >= 0; i--) {
      phraseGroup += i == verseNumber - 1 ? phrases[i] : otherPhrases[i];
    }
    return phraseGroup + "\n";
  }

  verses(start, end) {
    let phraseGroups = "";
    for (let i = start; i <= end; i++) {
      phraseGroups += this.verse(i) + "\n";
    }
    return phraseGroups;
  }
}
