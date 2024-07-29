package University;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CourseTime {
        private DayOfWeek day;
        private LocalTime startHour;
        private LocalTime endHour;

        public CourseTime() {
            this.day = DayOfWeek.SUNDAY;
            this.startHour = parseTime("00:00");
            this.endHour = parseTime("00:00");
        }
    
        public CourseTime(DayOfWeek day, String startHour, String endHour) {
            this.day = day;
            this.startHour = parseTime(startHour);
            this.endHour = parseTime(endHour);
        }
    
        public DayOfWeek getDay() {
            return day;
        }
    
        public LocalTime getStartHour() {
            return startHour;
        }
    
        public LocalTime getEndHour() {
            return endHour;
        }
    
        private LocalTime parseTime(String timeString) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                return LocalTime.parse(timeString, formatter);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid time format. Please use HH:mm format.");
            }
        }

        public void printHours() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            System.out.println("Start Hour: " + startHour.format(formatter));
            System.out.println("End Hour: " + endHour.format(formatter));
        }

        //toString
        public String toString() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            return day + " " + startHour.format(formatter) + " - " + endHour.format(formatter);
        }
    }
