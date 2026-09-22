export const isValid = (isbn = "") => {
  const isbn10 = isbn.replace(/-/g, '');
  if (isbn10.length != 10) {
    return false;
  }
  let total = 0;
  for (let i = 0; i < isbn10.length; i++) {
    const n = isbn10.length - i;
    if (i === isbn10.length - 1 && isbn10[i] === "X") {
      total += 10 * n;
      continue;
    }
    if (/\d/.test(isbn10[i])) {
      total += parseInt(isbn10[i]) * n;
    }
    else {
      return false;
    }
  }
  return total % 11 === 0;
};