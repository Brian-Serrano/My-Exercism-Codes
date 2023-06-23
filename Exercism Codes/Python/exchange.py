def exchange_money(budget, exchange_rate):
    return budget / exchange_rate
def get_change(budget, exchanging_value):
    return budget - exchanging_value
def get_value_of_bills(denomination, number_of_bills):
    return denomination * number_of_bills
def get_number_of_bills(budget, denomination):
    return int(budget / denomination)
def get_leftover_of_bills(budget, denomination):
    return budget % denomination
def exchangeable_value(budget, exchange_rate, spread, denomination):
    new_rate = exchange_rate + (exchange_rate * (spread / 100)) 
    total_new_currency = exchange_money(budget, new_rate)
    bill_value_new_currency = int(total_new_currency / denomination)
    maximun_value_new_currency = bill_value_new_currency * denomination
    return maximun_value_new_currency