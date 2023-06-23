export const score = (x, y) => {
    const distance = Math.sqrt(x ** 2 + y ** 2);
  
    if (distance > 10) {
      return 0;
    } else if (distance > 5) {
      return 1;
    } else if (distance > 1) {
      return 5;
    } else {
      return 10;
    }
  };