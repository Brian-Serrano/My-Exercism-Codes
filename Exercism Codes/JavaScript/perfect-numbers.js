export const classify = (n) => {
    if (n <= 0) throw new Error('Classification is only possible for natural numbers.');
    const aliquot = [...new Array(Math.ceil(n ** 0.5)).keys()]
        .reduce((a, c) => n % c == 0 ? a + c + (c == 1 ? 0 : n / c) : a, 0);
    return aliquot == n ? 'perfect' : aliquot < n ? 'deficient' : 'abundant';
};
