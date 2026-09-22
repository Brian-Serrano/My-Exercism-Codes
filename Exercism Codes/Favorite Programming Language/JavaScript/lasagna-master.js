export const cookingStatus = (time) => {
    if(time == 0) return 'Lasagna is done.';
    else if(isNaN(time)) return 'You forgot to set the timer.';
    else return 'Not done, please wait.';
  };
  
  export const preparationTime = (layer, time = 2) => {
    return layer.length * time;
  };
  
  export const quantities = (layer) => {
    let [n, s] = layer.reduce((count, current) => {
      if(current == 'noodles') count[0]++;
      if(current == 'sauce') count[1]++;
      return count;
    }, [0, 0]);
    return { noodles: n * 50, sauce: s * 0.2 };
  };
  
  export const addSecretIngredient = (friend, mine) => {
    mine.push(friend[friend.length - 1]);
  };
  
  export const scaleRecipe = (recipe, num) => {
    num /= 2;
    return Object.keys(recipe).reduce((acc, key) => {
      acc[key] = recipe[key] * num;
      return acc;
    }, {});
  };