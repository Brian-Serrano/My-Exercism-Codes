# Constant for the expected bake time of 40 minutes
EXPECTED_BAKE_TIME = 40


def bake_time_remaining(actual_bake_time):
    """
    Calculates the remaining bake time given the actual bake time.

    Parameters:
    actual_bake_time (int): The actual bake time in minutes.

    Returns:
    int: The remaining bake time.
    """
    return EXPECTED_BAKE_TIME - actual_bake_time


def preparation_time_in_minutes(prep_time_in_hours):
    """
    Converts preparation time in hours to minutes.

    Parameters:
    prep_time_in_hours (int): The preparation time in hours.

    Returns:
    int: The preparation time in minutes.
    """
    return prep_time_in_hours * 2


def elapsed_time_in_minutes(prep_time_in_hours, actual_bake_time):
    """
    Calculates the elapsed time in minutes given the preparation time in hours and actual bake time.

    Parameters:
    prep_time_in_hours (int): The preparation time in hours.
    actual_bake_time (int): The actual bake time in minutes.

    Returns:
    int: The elapsed time in minutes.
    """
    return actual_bake_time + preparation_time_in_minutes(prep_time_in_hours)

