def is_criticality_balanced(temperature, neutrons_emitted):
    return temperature < 800 and neutrons_emitted > 500 and temperature * neutrons_emitted < 500000


def reactor_efficiency(voltage, current, theoretical_max_power):
    generated_power = voltage * current
    res = (generated_power/theoretical_max_power)*100
    if res >= 80:
        return "green"
    elif res < 80 and res >= 60:
        return "orange"
    elif res < 60 and res >= 30:
        return "red"
    else:
        return "black"


def fail_safe(temperature, neutrons_produced_per_second, threshold):
    res = ((temperature * neutrons_produced_per_second) / threshold) * 100
    if res < 90:
        return "LOW"
    elif res >= 90 and res < 110:
        return "NORMAL"
    else:
        return "DANGER"
