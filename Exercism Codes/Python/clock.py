from datetime import datetime, time, timedelta


class Clock:
    def __init__(self, hour, minute):
        self.time = datetime.combine(datetime.today(), time.min) + timedelta(hours=hour, minutes=minute)

    def __repr__(self):
        return f"Clock({self.time.hour}, {self.time.minute})"

    def __str__(self):
        return f"{self.time.hour:02d}:{self.time.minute:02d}"

    def __eq__(self, other):
        return self.time.hour == other.time.hour and self.time.minute == other.time.minute

    def __add__(self, minutes):
        new_time = self.time + timedelta(minutes=minutes)
        return Clock(new_time.hour, new_time.minute)

    def __sub__(self, minutes):
        new_time = self.time - timedelta(minutes=minutes)
        return Clock(new_time.hour, new_time.minute)
