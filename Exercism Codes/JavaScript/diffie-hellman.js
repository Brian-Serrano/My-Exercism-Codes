//
// This is only a SKELETON file for the 'Diffie Hellman' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export class DiffieHellman {
  constructor(p, g) {
    if (g < 2 || g > p - 1)
      throw new Error();
    if (!isPrime(p) || !isPrime(g))
      throw new Error();
    
    this.p = p;
    this.g = g;
  }

  getPublicKey(privateKey) {
    if (privateKey <= 1)
      throw new Error();
    if (privateKey >= this.p)
      throw new Error();
    return Math.pow(this.g, privateKey) % this.p;
  }

  getSecret(theirPublicKey, myPrivateKey) {
    return Math.pow(theirPublicKey, myPrivateKey) % this.p;
  }

  static getPrivateKey(p) {
    return 2 + Math.floor(Math.random() * (p - 2));
  }
}

const isPrime = (num) => {
    for(let i = 2, s = Math.sqrt(num); i <= s; i++) {
        if (num % i === 0)
          return false;
    }
    return true;
};
