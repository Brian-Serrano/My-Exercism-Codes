const mapping = [
    ['Methionine', ['AUG']],
    ['Phenylalanine', ['UUU', 'UUC']],
    ['Leucine', ['UUA', 'UUG']],
    ['Serine', ['UCU', 'UCC', 'UCA', 'UCG']],
    ['Tyrosine', ['UAU', 'UAC']],
    ['Cysteine', ['UGU', 'UGC']],
    ['Tryptophan', ['UGG']],
    ];
const stopCodons = ['UAA', 'UAG', 'UGA'];
export const translate = (codons = []) => {
    let proteins = [];
    for(let i = 0; i < codons.length; i += 3) {
        let sub = codons.substring(i, i + 3)
        let idx = mapping.findIndex(e => e[1].some(c => c === sub));
        let stop = stopCodons.some(e => e === sub);
        if(idx === -1 && !stop)
            throw new Error('Invalid codon');
        else {
            if (!stop) proteins.push(mapping[idx][0]);
            else break;
        }
    }
    return proteins;
};
