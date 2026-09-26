export const parse = (s) => s.split(/[ \-_]+/).map(c => c[0]).join('').toUpperCase();
