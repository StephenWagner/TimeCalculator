package timeCalculator;

import java.sql.Time;

public class TimeCalculator {

    public TimeCalculator() {

    }

    /**
     * Finds the amount of time passed between two points in a single day. Will not work if calculating through midnight
     * of a day...
     * 
     * @param beginTime
     *            Time of the first point in the day.
     * @param endTime
     *            Ending point to find the amount of time passed.
     * @return The amount of time which has passed between two points in the same day, returned as a TimeSW.
     */
    public TimeSW timePassed(TimeSW beginTime, TimeSW endTime) {
	TimeSW resultTime;

	int totalSeconds = endTime.getTotalSeconds() - beginTime.getTotalSeconds();

	resultTime = new TimeSW(totalSeconds);

	return resultTime;
    }

    /**
     * Find the amount of time passed between two times and returns it as a TimeSW.
     * 
     * @param beginTime
     *            The start time.
     * @param endTime
     *            The end time.
     * @return The total time passed between the two times passed in, given as TimeSW. If the begin time is later than
     *         the end time, a negative number will result.
     */
    public TimeSW timePassed(Time beginTime, Time endTime) {
	TimeSW resultTime;
	long totalMilliseconds;

	totalMilliseconds = endTime.getTime() - beginTime.getTime();
	resultTime = new TimeSW(totalMilliseconds);

	return resultTime;
    }

    // adds two amounts of time
    public TimeSW addTime(TimeSW firstTime, TimeSW secondTime) {
	TimeSW resultTime;

	int totalSeconds = secondTime.getTotalSeconds() + firstTime.getTotalSeconds();

	resultTime = new TimeSW(totalSeconds);
	return resultTime;
    }

    public TimeSW subtractTime(TimeSW time, TimeSW timeToSubract) {
	TimeSW resultTime;
	int totalSeconds = time.getTotalSeconds() - timeToSubract.getTotalSeconds();

	resultTime = new TimeSW(totalSeconds);
	return resultTime;
    }

    public TimeSW multiplyTime(TimeSW time, int multiplyBy) {
	TimeSW resultTime;
	int totalSeconds = time.getTotalSeconds() * multiplyBy;

	resultTime = new TimeSW(totalSeconds);
	return resultTime;
    }

}
