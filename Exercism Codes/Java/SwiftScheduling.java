import java.time.LocalDateTime;

public class SwiftScheduling {
    public static LocalDateTime convertToDeliveryDate(LocalDateTime meetingStart, String description) {
        if (description.equals("NOW")) {
            return meetingStart.plusHours(2);
        }
        if (description.equals("ASAP")) {
            if (meetingStart.getHour() < 13) {
                return meetingStart.withHour(17).withMinute(0).withSecond(0);
            }
            else {
                return meetingStart.withHour(13).withMinute(0).withSecond(0).plusDays(1);
            }
        }
        if (description.equals("EOW")) {
            int dayOfWeek = meetingStart.getDayOfWeek().ordinal();
            if (dayOfWeek <= 2) {
                return meetingStart.withHour(17).withMinute(0).withSecond(0).plusDays(4 - dayOfWeek);
            }
            else {
                return meetingStart.withHour(20).withMinute(0).withSecond(0).plusDays(6 - dayOfWeek);
            }
        }
        if (description.charAt(description.length() - 1) == 'M') {
            int n = Integer.parseInt(description.substring(0, description.length() - 1));
            LocalDateTime newMonth = meetingStart.getMonth().getValue() < n ?
                    meetingStart.withMonth(n).withDayOfMonth(1) :
                    meetingStart.plusYears(1).withMonth(n).withDayOfMonth(1);
            return findWorkday(newMonth, false);
        }
        if (description.charAt(0) == 'Q') {
            int n = Integer.parseInt(description.substring(1));
            LocalDateTime newMonth = meetingStart.getMonth().getValue() < n * 3 ?
                    meetingStart.withMonth(n * 3).plusMonths(1).withDayOfMonth(1).minusDays(1) :
                    meetingStart.plusYears(1).withMonth(n * 3).plusMonths(1).withDayOfMonth(1).minusDays(1);
            return findWorkday(newMonth, true);
        }

        return null;
    }

    public static LocalDateTime findWorkday(LocalDateTime date, boolean reverse) {
        while (date.getDayOfWeek().ordinal() > 4) {
            date = reverse ? date.minusDays(1) : date.plusDays(1);
        }

        return date.withHour(8).withMinute(0).withSecond(0);
    }
}
