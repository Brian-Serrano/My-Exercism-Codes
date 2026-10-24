export const classify = (n) => {
  if (n <= 0) throw new Error('Classification is only possible for natural numbers.');
  const aliquot = Array.from({ length: n - 1 }, (_, i) => i + 1)
    .filter(c => n % c == 0)
    .reduce((a, c) => a + c, 0);
  return aliquot == n ? 'perfect' : aliquot < n ? 'deficient' : 'abundant';
};
