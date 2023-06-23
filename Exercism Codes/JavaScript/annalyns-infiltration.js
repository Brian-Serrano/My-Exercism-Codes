export function canExecuteFastAttack(knightIsAwake) {
    return !knightIsAwake;
  }
  
  export function canSpy(knightIsAwake, archerIsAwake, prisonerIsAwake) {
    return knightIsAwake || archerIsAwake || prisonerIsAwake;
  }
  
  export function canSignalPrisoner(archerIsAwake, prisonerIsAwake) {
    return prisonerIsAwake && !archerIsAwake;
  }
  
  export function canFreePrisoner(
    knightIsAwake,
    archerIsAwake,
    prisonerIsAwake,
    petDogIsPresent
  ) {
    if(petDogIsPresent){
      return !archerIsAwake;
    } else {
      return prisonerIsAwake && !knightIsAwake && !archerIsAwake;
    }
  }