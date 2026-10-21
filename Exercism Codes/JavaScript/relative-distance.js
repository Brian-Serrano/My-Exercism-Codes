export const degreesOfSeparation = (familyTree, personA, personB) => {
  const newFamilyTree = Object.entries(familyTree).map(p => [p[0], ...p[1]]);
  const value = [];

  const find = (person, visited) => {
    if (person == personB) {
      value.push(visited.length - 1);
      return;
    }
    for (const node of newFamilyTree) {
      if (node.includes(person)) {
        for (const n of node) {
          if (person != n && !visited.includes(n)) {
            find(n, [...visited, n]);
          }
        }
      }
    }
  };

  find(personA, [personA]);

  return value.length > 0 ? Math.min(...value) : -1;
};
