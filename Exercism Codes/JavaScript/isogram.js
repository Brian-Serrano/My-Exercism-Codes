export const isIsogram = (s) => {
    return new Set(s
                 .replace(/ |-/, '')
                 .toLowerCase()
                 .split(''))
    .size == s.replace(/ |-/, '')
    .length;
};
