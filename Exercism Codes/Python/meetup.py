import calendar
import datetime


class MeetupDayException(ValueError):
    def __init__(self, message):
        super().__init__(message)

days = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"]

def meetup(year, month, week, day_of_week):
    last_day = calendar.monthrange(year, month)[1]
    if week == "first":
        return find_day(1, 7, day_of_week, year, month)
    elif week == "second":
        return find_day(8, 14, day_of_week, year, month)
    elif week == "third":
        return find_day(15, 21, day_of_week, year, month)
    elif week == "fourth":
        return find_day(22, 28, day_of_week, year, month)
    elif week == "fifth":
        return find_day(29, last_day, day_of_week, year, month)
    elif week == "last":
        return find_day(last_day - 6, last_day, day_of_week, year, month)
    elif week == "teenth":
        return find_day(13, 19, day_of_week, year, month)

    return find_day(1, 7, day_of_week, year, month)

def find_day(start, end, day_of_week, year, month):
    day = [n for n in range(start, end + 1)
           if days[datetime.date(year, month, n).weekday()] == day_of_week]

    if len(day) == 0:
        raise MeetupDayException("That day does not exist.")

    return datetime.date(year, month, day[0])