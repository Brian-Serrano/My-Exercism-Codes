//
// This is only a SKELETON file for the 'Lens Person' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

/* eslint-disable no-unused-vars */
import { Person } from './person';
import { Name } from './name';
import { Born } from './born';
import { Address } from './address';
import { Lens } from './lens';

// Implement the nameLens with the getter and setter
export const nameLens = new Lens(
  (person) => {
    return person.name;
  },
  (person, newName) => {
    return new Person(newName, person.born, person.address);
  },
);

// Implement the bornAtLens with the getter and setter
export const bornAtLens = new Lens(
  (person) => {
    return person.born.bornAt;
  },
  (person, birthAddress) => {
    return new Person(person.name, new Born(birthAddress, person.born.bornOn), person.address);
  },
);

// Implement the streetLens with the getter and setter
export const streetLens = new Lens(
  (person) => {
    return person.address.street;
  },
  (person, newStreet) => {
    return new Person(person.name, person.born, new Address(person.address.houseNumber, newStreet, person.address.place, person.address.country));
  },
);
