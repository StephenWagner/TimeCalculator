package timeCalculator;

public class TimeOfTheDay extends TimeSW {
    private Boolean am;

    public TimeOfTheDay() {
	this.setHours(0);
	this.setMinutes(0);
	this.setSeconds(0);
	this.setAm(true);
    }

    public TimeOfTheDay(int hour, int minute, int second, Boolean am) {
	if (hour < 13)
	    this.setHours(hour);
	else
	    this.setHours(0);

	this.setMinutes(minute);
	this.setSeconds(second);
	this.am = am;
    }

    /**
     * Constructor to be used if you want to construct with military time. This will automatically set the TimeOfTheDay
     * in am/pm format.
     * 
     * @param hour
     *            hour in military time (0-23).
     * @param minute
     *            minutes (0-59).
     * @param second
     *            seconds (0-59).
     */
    public TimeOfTheDay(int hour, int minute, int second) {
	//set hours and whether or not it's am
	if (hour == 0) {
	    this.setHours(12);
	    this.setAm(true);
	} else if (hour > 12) {
	    this.setHours(hour - 12);
	    this.setAm(false);
	} else
	    this.setHours(hour);
	this.setAm(true);
	
	//set minutes and seconds
	this.setMinutes(minute);
	this.setSeconds(second);
    }

    public Boolean getAm() {
	return am;
    }

    public void setAm(Boolean am) {
	this.am = am;
    }

    public int getTotalSeconds() {
	int secMin, secHour, totalSeconds, hourOffset = 1;

	// if after 1pm or later, do the offset
	if (!am && this.getHours() < 12)
	    hourOffset = 12;

	secMin = this.getMinutes() * 60;
	secHour = this.getHours() * 3600 * hourOffset;

	totalSeconds = secMin + secHour + this.getSeconds();

	return totalSeconds;
    }
}
