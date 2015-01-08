package timeCalculator;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeCalculator extends Applet implements ActionListener {
    /**
     * auto-generated
     */
    private static final long serialVersionUID = 1L;

    // global variables
    private Label beginTimeLabel, endTimeLabel, timeElapsedLabel, enterTimeLabel, totalTimeLabel;
    private TimeOfTheDay beginTime = new TimeOfTheDay();
    private TimeOfTheDay endTime = new TimeOfTheDay();
    private TimeSW totalHours = new TimeSW();
    private TimeSW timeElapsedHours = new TimeSW();
    private TimeSW enterTimeHours = new TimeSW();
    private Choice hours1, hours2, hours3, minutes1, minutes2, minutes3, meridian1, meridian2;
    private Button computeTimeButton, addElapsedButton, addEnteredButton, clearButton;

    public void init() {

	setLayout(new FlowLayout(FlowLayout.LEFT));
	String format = "%02d";

	// create the hours Choices and add the items
	hours1 = new Choice();
	hours2 = new Choice();
	hours3 = new Choice();

	for (int i = 0; i <= 12; i++) {
	    hours1.add(String.format(format, i));
	    hours2.add(String.format(format, i));
	    hours3.add(String.format(format, i));
	}

	// create the minutes choices and add the items
	minutes1 = new Choice();
	minutes2 = new Choice();
	minutes3 = new Choice();

	for (int i = 0; i < 60; i++) {
	    minutes1.add(String.format(format, i));
	    minutes2.add(String.format(format, i));
	    minutes3.add(String.format(format, i));

	}

	// create the meridian choices and add the items
	meridian1 = new Choice();
	meridian2 = new Choice();

	meridian1.add("am");
	meridian1.add("pm");
	meridian2.add("am");
	meridian2.add("pm");

	// add all items to the applet

	// select begin time and dropdowns
	beginTimeLabel = new Label("Select begin time:");
	add(beginTimeLabel);
	add(hours1);
	add(minutes1);
	add(meridian1);

	// select end time and dropdowns
	endTimeLabel = new Label("Select end time:");
	add(endTimeLabel);
	add(hours2);
	add(minutes2);
	add(meridian2);

	// compute time passed button and elapsed time label
	computeTimeButton = new Button("Compute Time Passed");
	computeTimeButton.addActionListener(this);
	add(computeTimeButton);
	timeElapsedLabel = new Label("Elapsed time: " + timeElapsedHours);
	add(timeElapsedLabel);

	// add elapsed to total button
	addElapsedButton = new Button("Add Elapsed to Total");
	addElapsedButton.addActionListener(this);
	add(addElapsedButton);

	// enter time to add label and dropdowns
	enterTimeLabel = new Label("Enter time to add:");
	add(enterTimeLabel);
	add(hours3);
	add(minutes3);
	addEnteredButton = new Button("Add Time to Total");
	addEnteredButton.addActionListener(this);
	add(addEnteredButton);

	totalTimeLabel = new Label("Total time: " + totalHours);
	add(totalTimeLabel);

	clearButton = new Button("Clear");
	clearButton.addActionListener(this);
	add(clearButton);

    }

    // defining actionPerformed
    public void actionPerformed(ActionEvent e) {
	Object source = e.getSource();

	if (source == computeTimeButton) {
	    int hr1, hr2;
	    Boolean pm1, pm2;

	    hr1 = Integer.parseInt(hours1.getSelectedItem());
	    hr2 = Integer.parseInt(hours2.getSelectedItem());
	    pm1 = meridian1.getSelectedIndex() == 1;
	    pm2 = meridian2.getSelectedIndex() == 1;

	    // if begin time is 1pm or later, add 12 to the hours
	    if (hr1 != 12 && pm1) {
		hr1 += 12;
	    }
	    // if begin time hour is 12am, set hours to 0
	    else if (!pm1 && hr1 == 12)
		hr1 = 0;

	    // if end time is 1pm or later, add 12 to the hours
	    if (pm2 && hr2 != 12) {
		hr2 += 12;
	    }
	    // if end time hour is 12am, set hours to 0
	    else if (!pm2 && hr2 == 12)
		hr2 = 0;

	    beginTime.setHours(hr1);
	    beginTime.setMinutes(Integer.parseInt(minutes1.getSelectedItem()));
	    endTime.setHours(hr2);
	    endTime.setMinutes(Integer.parseInt(minutes2.getSelectedItem()));

	    timeElapsedHours = timePassed(beginTime, endTime);

	}

	if (source == addElapsedButton) {
	    totalHours = addTime(timeElapsedHours, totalHours);
	}

	if (source == addEnteredButton) {
	    enterTimeHours.setHours(Integer.parseInt(hours3.getSelectedItem()));
	    enterTimeHours.setMinutes(Integer.parseInt(minutes3.getSelectedItem()));
	    totalHours = addTime(enterTimeHours, totalHours);
	}

	if (source == clearButton) {
	    totalHours.clear();
	    timeElapsedHours.clear();
	}

	repaint();
    }

    public void paint(Graphics g) {
	timeElapsedLabel.setText("Elapsed time: " + timeElapsedHours);
	totalTimeLabel.setText("Total time: " + totalHours);
    }

    // Finds the amount of time passed
    public TimeSW timePassed(TimeSW beginTime, TimeSW endTime) {
	TimeSW resultTime;

	int totalSeconds = endTime.getTotalSeconds() - beginTime.getTotalSeconds();

	resultTime = new TimeSW(totalSeconds);

	return resultTime;
    }

    // adds two amounts of time
    public TimeSW addTime(TimeSW firstTime, TimeSW secondTime) {
	TimeSW resultTime;

	int totalSeconds = secondTime.getTotalSeconds() + firstTime.getTotalSeconds();

	resultTime = new TimeSW(totalSeconds);
	return resultTime;
    }

    public TimeSW subtractTime(TimeSW time, TimeSW timeToSubract){
	TimeSW resultTime;
	int totalSeconds = time.getTotalSeconds() - timeToSubract.getTotalSeconds();
	
	resultTime = new TimeSW(totalSeconds);
	return resultTime;
    }
}
