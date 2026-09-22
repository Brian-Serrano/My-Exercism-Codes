import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class AppointmentScheduler {
    public LocalDateTime schedule(String appointmentDateDescription) {
        return LocalDateTime.parse(appointmentDateDescription, DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"));
    }

    public boolean hasPassed(LocalDateTime appointmentDate) {
        return appointmentDate.isBefore(LocalDateTime.now());
    }

    public boolean isAfternoonAppointment(LocalDateTime appointmentDate) {
        int hour = appointmentDate.getHour();
        return hour >= 12 && hour < 18;
    }

    public String getDescription(LocalDateTime appointmentDate) {
        int hour = appointmentDate.getHour();
        String amOrPm = hour >= 12 ? "PM" : "AM";
        int hour12 = hour == 12 ? 12 : hour % 12;
        return "You have an appointment on " + toTitleCase(appointmentDate.getDayOfWeek().toString()) + ", " + toTitleCase(appointmentDate.getMonth().toString()) + " " + appointmentDate.getDayOfMonth() + ", " + appointmentDate.getYear() + ", at " + hour12 + ":" + String.format("%02d", appointmentDate.getMinute()) + " " + amOrPm + ".";
    }

    public LocalDate getAnniversaryDate() {
        return LocalDate.of(LocalDate.now().getYear(), 9, 15);
    }

    private String toTitleCase(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }
}
