import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class BafflingBirthdays {
    boolean sharedBirthday(List<LocalDate> birthdates) {
        for (int i = 0; i < birthdates.size(); i++) {
            LocalDate bdToCheck = birthdates.get(i);
            for (int j = 0; j < birthdates.size(); j++) {
                int day = bdToCheck.getDayOfMonth();
                int month = bdToCheck.getMonthValue();
                int day2 = birthdates.get(j).getDayOfMonth();
                int month2 = birthdates.get(j).getMonthValue();
                if (day == day2 && month == month2 && i != j) {
                    return true;
                }
            }
        }
        return false;
    }

    List<LocalDate> randomBirthdates(int groupSize) {
        List<LocalDate> result = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < groupSize; i++) {
            result.add(randomBirthdate(random));
        }
        return result;
    }

    LocalDate randomBirthdate(Random random) {
        int dayOfYear = random.nextInt(365);

        return LocalDate.of(2001, 1, 1).plusDays(dayOfYear);
    }

    double estimatedProbabilityOfSharedBirthday(int groupSize) {
        Random random = new Random();
        int matches = 0;
        int simulations = 5000;

        for (int i = 0; i < simulations; i++) {
            List<LocalDate> birthdays = new ArrayList<>();

            for (int j = 0; j < groupSize; j++) {
                birthdays.add(randomBirthdate(random));
            }

            if (sharedBirthday(birthdays)) {
                matches++;
            }
        }

        return ((double) matches / simulations) * 100;
    }
}