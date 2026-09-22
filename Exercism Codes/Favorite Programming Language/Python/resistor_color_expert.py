def resistor_label(colors):
    res_colors = [
        "black", "brown", "red", "orange",
        "yellow", "green", "blue", "violet",
        "grey", "white"
    ]
    tol_colors = {
        "grey": "±0.05%", "violet": "±0.1%", "blue": "±0.25%",
        "green": "±0.5%", "brown": "±1%", "red": "±2%",
        "gold": "±5%", "silver": "±10%"
    }

    if len(colors) == 1:
        return str(res_colors.index(colors[0])) + " ohms"

    if len(colors) == 4:
        result = int(str(res_colors.index(colors[0])) + str(res_colors.index(colors[1])) + ("0" * res_colors.index(colors[2])))
        return add_unit(result) + " " + tol_colors[colors[3]]

    if len(colors) == 5:
        result = int(str(res_colors.index(colors[0])) + str(res_colors.index(colors[1])) + str(res_colors.index(colors[2])) + ("0" * res_colors.index(colors[3])))
        return add_unit(result) + " " + tol_colors[colors[4]]

    return ""

def add_unit(x):
    if x >= 1000000000:
        return str(remove_trailing_0(x / 1000000000)) + " gigaohms"
    elif x >= 1000000:
        return str(remove_trailing_0(x / 1000000)) + " megaohms"
    elif x >= 1000:
        return str(remove_trailing_0(x / 1000)) + " kiloohms"
    else:
        return str(x) + " ohms"

def remove_trailing_0(x):
    return int(x) if x % 1 == 0 else x