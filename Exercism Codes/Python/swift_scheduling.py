from datetime import datetime, timedelta


def parse(date):
    return datetime.strptime(date, "%Y-%m-%dT%H:%M:%S")

def fmt(date):
    return date.strftime("%Y-%m-%dT%H:%M:%S")

def find_workday(date, reverse=False):
    while date.weekday() not in [0, 1, 2, 3, 4]:
        date = date - timedelta(days=1) if reverse else date + timedelta(days=1)

    return date.replace(hour=8, minute=0, second=0)

def get_last_day_of_month(date):
    if date.month == 12:
        return date.replace(year=date.year + 1, month=1) - timedelta(days=1)
    else:
        return date.replace(month=date.month + 1) - timedelta(days=1)

def delivery_date(start, description):
    date = parse(start)

    if description == "NOW":
        return fmt(date + timedelta(hours=2))
    if description == "ASAP":
        if date.hour < 13:
            return fmt(date.replace(hour=17, minute=0, second=0))
        else:
            return fmt(date.replace(hour=13, minute=0, second=0) + timedelta(days=1))
    if description == "EOW":
        if 0 <= date.weekday() <= 2:
            return fmt(date.replace(hour=17, minute=0, second=0) + timedelta(days=4 - date.weekday()))
        else:
            return fmt(date.replace(hour=20, minute=0, second=0) + timedelta(days=6 - date.weekday()))

    if description[-1] == "M":
        n = int(description[:-1])
        if date.month < n:
            new_month = date.replace(month=n, day=1)
        else:
            new_month = date.replace(year=date.year + 1, month=n, day=1)

        return fmt(find_workday(new_month))

    if description[0] == "Q":
        n = int(description[1:])
        if date.month < n * 3:
            new_month = get_last_day_of_month(date.replace(month=n * 3, day=1))
        else:
            new_month = get_last_day_of_month(date.replace(year=date.year + 1, month=n * 3, day=1))

        return fmt(find_workday(new_month, True))

    return None