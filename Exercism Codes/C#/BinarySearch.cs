public static class BinarySearch
{
    public static int Find(int[] input, int value)
    {
        int left = 0;
        int right = input.Length - 1;

        while (left <= right)
        {
            int mid = left + (right - left) / 2;
            if (input[mid] == value)
            {
                return mid;
            }
            else if (input[mid] < value)
            {
                left = mid + 1;
            }
            else
            {
                right = mid - 1;
            }
        }

        return -1;
    }
}