def find(search_list, value):
    left = 0
    right = len(search_list) - 1
    while left <= right:
        mid = left + ((right - left) // 2)

        if search_list[mid] == value:
            return mid

        if search_list[mid] < value:
            left = mid + 1

        if search_list[mid] > value:
            right = mid - 1

    raise ValueError("value not in array")
