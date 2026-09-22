export const findAnagrams = (w, l) => {
    const sorter = s => s.toLowerCase().split('').toSorted().join('');
    return l.filter(s => sorter(s) == sorter(w) && s.toLowerCase() != w.toLowerCase());
};
