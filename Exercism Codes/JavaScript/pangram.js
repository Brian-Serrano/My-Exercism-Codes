export const isPangram = (str) => {
    return [...new Array(26).keys()].every(x => str.toLowerCase().includes(String.fromCharCode(x + 97)));
};
