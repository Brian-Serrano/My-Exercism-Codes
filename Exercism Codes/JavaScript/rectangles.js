export function count(diagram) {
  if (diagram.length == 0) return 0;
  
  const isCorner = (r, c) => diagram[r][c] == '+';
  
  const isExternalLine = (r, c0, c1) => diagram[r].substring(c0, c1+1).match(/^\+[+-]*\+$/);
  
  const isInternalLine = (r, c0, c1) => diagram[r].substring(c0, c1+1).match(/^[+|].*[+|]$/);
  
  let numRect = 0;
  const numRows = diagram.length;
  const numCols = diagram[0].length;
  for (let r0 = 0; r0 < numRows; r0++) {
    for (let c0 = 0; c0 < numCols; c0++) {
      if (!isCorner(r0, c0)) continue;
      // Find other corner to the right on the same line.
      for (let c1 = c0+1; c1 < numCols; c1++) {
        if (!isExternalLine(r0, c0, c1)) continue;
        // go down until we find the other corners (or a line that isn't be an internal line)
        for (let r1 = r0+1; r1 < numRows; r1++) {
          if (!isInternalLine(r1, c0, c1)) break;
          if (isExternalLine(r1, c0, c1)) numRect++
        }
      }
    }
  }
  return numRect;
}