export function timeToMixJuice(name) {
    switch(name) {
      case 'Pure Strawberry Joy':
        return 0.5;
      case 'Energizer':
        return 1.5;
      case 'Green Garden':
        return 1.5;
      case 'Tropical Island':
        return 3;
      case 'All or Nothing':
        return 5;
      default:
        return 2.5;
    }
  }
  export function limesToCut(wedgesNeeded, limes) {
    var i = 0;
    var wedges = 0;
    while (wedges < wedgesNeeded && i < limes.length) {
      switch(limes[i]) {
        case 'small':
          wedges += 6;
          break;
        case 'medium':
          wedges += 8;
          break;
        case 'large':
          wedges += 10;
          break;
        default:
          throw new Error(`Invalid lime size: ${limes[i]}`);
      }
      i++;
    }
    return i;
  }
  export function remainingOrders(timeLeft, orders) {
    var totalTime = 0;
    var i = 0;
    while (totalTime < timeLeft && i < orders.length) {
      totalTime += timeToMixJuice(orders[i]);
      i++;
    }
    return orders.slice(i);
  }