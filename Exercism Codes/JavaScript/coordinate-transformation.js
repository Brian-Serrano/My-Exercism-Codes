export function translate2d(dx, dy) {
    return (x, y) => [dx + x, dy + y];
  }
  
  export function scale2d(sx, sy) {
    return (x, y) => [sx * x, sy * y];
  }
  
  export function composeTransform(f, g) {
    return (x, y) => g(...f(x, y));
  }
  
  export function memoizeTransform(f) {
    let prevX, prevY, prevRes;
    return (x, y) => {
      if(prevX == x && prevY == y){
        return prevRes;
      }
      prevX = x;
      prevY = y;
      return prevRes = f(x, y);
    };
  }