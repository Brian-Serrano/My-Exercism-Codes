def create_inventory(items, inventory={}):
    for i in items:
        inventory[i] = inventory[i] + 1 if i in inventory.keys() else 1
    return inventory


def add_items(inventory, items):
    return create_inventory(items, inventory)


def decrement_items(inventory, items):
    for i in items:
        if inventory[i] > 0:
            inventory[i] -= 1
    return inventory


def remove_item(inventory, item):
    inventory.pop(item, '')
    return inventory


def list_inventory(inventory):
    lists = []
    for i in inventory:
        if inventory[i] > 0:
            lists.append((i, inventory[i]))
    return lists
