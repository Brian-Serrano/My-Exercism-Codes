#!/usr/bin/env node

const fs = require('fs');
const path = require('path');

/**
 * Reads the given file and returns lines.
 *
 * This function works regardless of POSIX (LF) or windows (CRLF) encoding.
 *
 * @param {string} file path to file
 * @returns {string[]} the lines
 */
function readLines(file) {
  const data = fs.readFileSync(path.resolve(file), { encoding: 'utf-8' });
  return data.split(/\r?\n/);
}

const VALID_OPTIONS = [
  'n', // add line numbers
  'l', // print file names where pattern is found
  'i', // ignore case
  'v', // reverse files results
  'x', // match entire line
];

const ARGS = process.argv;

//
// This is only a SKELETON file for the 'Grep' exercise. It's been provided as a
// convenience to get you started writing code faster.
//
// This file should *not* export a function. Use ARGS to determine what to grep
// and use console.log(output) to write to the standard output.

const flags = [];
const files = [];
let pattern = "";

for (const arg of ARGS) {
  if (arg.endsWith(".txt")) {
    files.push(arg);
  }
  else if (arg.startsWith("-")) {
    flags.push(arg);
  }
  else if (!arg.includes("/")) {
    pattern = arg;
  }
}

const lst = [];
for (const filename of files) {
  let lineNumber = 0;
  for (const line of readLines(filename)) {
    lineNumber++;
    const pat = flags.includes("-x") ? `^(?:${pattern})$` : pattern;
    const regex = flags.includes("-i") ? new RegExp(pat, "i") : new RegExp(pat);
    const match = regex.test(line);
    if (flags.includes("-v") ? !match : match) {
      const number = flags.includes("-n") ? `${lineNumber}:` : "";
      lst.push([filename, number + line]);
    }
  }
}

if (flags.includes("-l")) {
  console.log([...new Set(lst.map(c => c[0]))].join("\n"));
}
else {
  console.log(lst.map(c => files.length > 1 ? `${c[0]}:${c[1]}` : c[1]).join("\n"));
}