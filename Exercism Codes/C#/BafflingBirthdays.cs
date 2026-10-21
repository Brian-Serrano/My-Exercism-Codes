public static class BafflingBirthdays
{
    public static DateOnly[] RandomBirthdates(int numberOfBirthdays)
    {
        List<DateOnly> result = new List<DateOnly>();
        Random random = new Random();
        for (int i = 0; i < numberOfBirthdays; i++)
        {
            result.Add(RandomBirthdate(random));
        }
        return result.ToArray();
    }

    private static DateOnly RandomBirthdate(Random random)
    {
        return new DateOnly(2001, 1, 1).AddDays(random.Next(365));
    }

    public static bool SharedBirthday(DateOnly[] birthdays)
    {
        for (int i = 0; i < birthdays.Length; i++)
        {
            DateOnly bd = birthdays[i];
            for (int j = 0; j < birthdays.Length; j++)
            {
                int month = birthdays[j].Month;
                int day = birthdays[j].Day;
                if (month == bd.Month && day == bd.Day && i != j)
                    return true;
            }
        }
        return false;
    }
    
    public static double EstimatedProbabilityOfSharedBirthday(int numberOfBirthdays)
    {
        Random random = new Random();
        int matches = 0;
        int simulations = 5000;

        for (int i = 0; i < simulations; i++)
        {
            List<DateOnly> birthdays = new List<DateOnly>();

            for (int j = 0; j < numberOfBirthdays; j++)
            {
                birthdays.Add(RandomBirthdate(random));
            }

            if (SharedBirthday(birthdays.ToArray()))
            {
                matches++;
            }
        }

        return ((double)matches / simulations) * 100;
    }
}
