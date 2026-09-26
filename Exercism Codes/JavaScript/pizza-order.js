const PIZZAS = {
  'Margherita': 7, 'Caprese': 9, 'Formaggio': 10, 'ExtraSauce': 1, 'ExtraToppings': 2
};

export function pizzaPrice(pizza, ...extras) {
  return extras.reduce((acc, next) => acc + PIZZAS[next], PIZZAS[pizza]);
}

export function orderPrice(pizzaOrders) {
  return pizzaOrders.reduce((acc, next) => acc + pizzaPrice(next.pizza, ...next.extras), 0);
}
