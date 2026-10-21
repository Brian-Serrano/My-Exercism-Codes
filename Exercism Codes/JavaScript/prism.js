//
// This is only a SKELETON file for the 'Prism' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const findSequence = (laser, prisms) => {
  let cx = laser.x;
  let cy = laser.y;
  let cAngle = toPositiveAngle(laser.angle);
  const result = [];

  while (true) {
    const paths = [];
    for (const prism of prisms) {
      const notSameLocation = !(prism.y - cy == 0 && prism.x - cx == 0);
      const rAngle = Math.atan2(prism.y - cy, prism.x - cx);
      const angle = toPositiveAngle(rAngle * (180 / Math.PI));
      const difference = Math.abs(angle - cAngle);
      const angleEqual = Math.min(difference, 360.0 - difference) <= 0.005;
      if (notSameLocation && angleEqual)
        paths.push(prism);
    }

    if (paths.length == 0)
      break;

    if (paths.length > 1) {
      const nearestPrism = paths.reduce((best, p) => getMin(p, cx, cy) < getMin(best, cx, cy) ? p : best);

      result.push(nearestPrism.id);
      cx = nearestPrism.x;
      cy = nearestPrism.y;
      cAngle = toPositiveAngle(cAngle + nearestPrism.angle);
    }
    else {
      result.push(paths[0].id);
      cx = paths[0].x;
      cy = paths[0].y;
      cAngle = toPositiveAngle(cAngle + paths[0].angle);
    }
  }

  return result;
};

const toPositiveAngle = (angle) => {
  const value = angle - Math.floor(angle / 360.0) * 360.0;
  return Math.round(value * 1000.0) / 1000.0;
};

const getMin = (p, currentX, currentY) => {
  const dx = p.x - currentX;
  const dy = p.y - currentY;
  return dx * dx + dy * dy;
}
