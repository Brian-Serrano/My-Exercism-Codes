def add_item(current_cart, items_to_add):
    for item in items_to_add:
        current_cart[item] = current_cart[item] + 1 if item in current_cart.keys() else 1

    return current_cart

def read_notes(notes):
    return add_item({}, notes)


def update_recipes(ideas, recipe_updates):
    for key, value in recipe_updates:
        ideas[key] = value

    return ideas


def sort_entries(cart):
    return dict(sorted(cart.items()))


def send_to_store(cart, aisle_mapping):
    for key, value in cart.items():
        cart[key] = [value, *aisle_mapping[key]]

    return dict(reversed(sort_entries(cart).items()))


def update_store_inventory(fulfillment_cart, store_inventory):
    for key, value in store_inventory.items():
        if key not in fulfillment_cart.keys():
            continue

        store_inventory[key][0] -= fulfillment_cart[key][0]

        if store_inventory[key][0] <= 0:
            store_inventory[key][0] = "Out of Stock"

    return store_inventory