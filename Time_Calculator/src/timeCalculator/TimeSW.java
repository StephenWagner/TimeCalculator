package timeCalculator;

public class TimeSW {
    private int hour, min, sec;

    public TimeSW() {
	hour = min = sec = 0;
    }

    /**
     * Constructor. Stores time when you give it the hour, minute, and second. This class can be used to store amounts
     * of time (time greater than 24 hours). Stores it and gives a toString() in HH:MM:SS format.
     * 
     * @param hours
     *            Hour or the day or total hours you want to store.
     * @param minutes
     *            Total minutes (0-59).
     * @param seconds
     *            Total seconds (0-59).
     */
    public TimeSW(int hours, int minutes, int seconds) {
	hour = hours;
	min = minutes;
	sec = seconds;
    }

    /**
     * Use this constructor when you have the total time exclusively in seconds. Used to help in math operations.
     * 
     * @param totalSeconds
     *            All of the time as represented in seconds.
     */
    public TimeSW(int totalSeconds) {
	this.setHoursWithTotalSeconds(totalSeconds);
    }

    public int getHours() {
	return hour;
    }

    public void setHours(int hour) {
	this.hour = hour;
    }

    public int getMinutes() {
	return min;
    }

    public void setMinutes(int min) {
	this.min = min;
    }

    public int getSeconds() {
	return sec;
    }

    public void setSeconds(int sec) {
	this.sec = sec;
    }

    public String toString() {
	String format = "%02d";

	return String.format(format, hour) + ":" + String.format(format, min) + ":" + String.format(format, sec);
    }

    public void setHoursWithTotalSeconds(int totalSeconds) {
	int remainder;
	hour = totalSeconds / 3600;
	remainder = totalSeconds % 3600;
	min = remainder / 60;
	sec = remainder % 60;
    }

    public void clear() {
	this.hour = this.min = this.sec = 0;
    }

    public int getTotalSeconds() {
	int secMin, secHour, totalSeconds;

	secMin = this.getMinutes() * 60;
	secHour = this.getHours() * 3600;

	totalSeconds = secMin + secHour + this.getSeconds();

	return totalSeconds;
    }

}
