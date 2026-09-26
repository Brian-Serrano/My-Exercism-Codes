def generate_seat_letters(number):
    seat_names = ["A", "B", "C", "D"]

    for n in range(number):
        yield seat_names[n % 4]

def generate_seats(number):
    for i, n in enumerate(generate_seat_letters(number)):
        yield str(i // 4 + 1 + (0 if i // 4 + 1 < 13 else 1)) + n

def assign_seats(passengers):
    result = {}

    for i, n in enumerate(generate_seats(len(passengers))):
        result[passengers[i]] = n

    return result

def generate_codes(seat_numbers, flight_id: str):
    for seat_number in seat_numbers:
        yield (seat_number + flight_id).ljust(12, "0")