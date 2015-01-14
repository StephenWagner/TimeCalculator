package timeCalculator;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Choice;
//import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;

public class TimeCalculatorWindow extends Applet implements ActionListener {
    /**
     * auto-generated
     */
    private static final long serialVersionUID = 1L;

    // global variables
    private Label beginTimeLabel, endTimeLabel, timeElapsedLabel, enterTimeLabel, totalTimeLabel;
    private Time beginTime = new Time(0);
    private Time endTime = new Time(0);
    private TimeSW totalHours = new TimeSW();
    private TimeSW timeElapsedHours = new TimeSW();
    private TimeSW enterTimeHours = new TimeSW();
    private Choice hours1, hours2, hours3, minutes1, minutes2, minutes3, meridian1, meridian2;
    private Button computeTimeButton, addElapsedButton, addEnteredButton, clearButton;
    private TimeCalculator calc;
    private Panel p1, p2, p3, p4, p5, p6, p7;
    private StringBuffer totalListed = new StringBuffer();

    public void init() {

	// setLayout(new FlowLayout(FlowLayout.LEFT));
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

	p1 = new Panel();

	// select begin time and dropdowns
	beginTimeLabel = new Label("Select begin time:");
	p1.add(beginTimeLabel);
	p1.add(hours1);
	p1.add(minutes1);
	p1.add(meridian1);
	add(p1, LEFT_ALIGNMENT);

	p2 = new Panel();
	// select end time and dropdowns
	endTimeLabel = new Label("Select end time:");
	p2.add(endTimeLabel);
	p2.add(hours2);
	p2.add(minutes2);
	p2.add(meridian2);
	add(p2, LEFT_ALIGNMENT);

	p3 = new Panel();
	// compute time passed button and elapsed time label
	computeTimeButton = new Button("Compute Time Passed");
	computeTimeButton.addActionListener(this);
	p3.add(computeTimeButton, CENTER_ALIGNMENT);
	timeElapsedLabel = new Label("Elapsed time: " + timeElapsedHours);
	p3.add(timeElapsedLabel, CENTER_ALIGNMENT);
	add(p3, CENTER_ALIGNMENT);

	p4 = new Panel();
	// add elapsed to total button
	addElapsedButton = new Button("Add Elapsed to Total");
	addElapsedButton.addActionListener(this);
	p4.add(addElapsedButton, CENTER_ALIGNMENT);
	add(p4, CENTER_ALIGNMENT);

	p5 = new Panel();
	// enter time to add label and dropdowns
	enterTimeLabel = new Label("Enter time to add:");
	p5.add(enterTimeLabel);
	p5.add(hours3);
	p5.add(minutes3);
	addEnteredButton = new Button("Add Time to Total");
	addEnteredButton.addActionListener(this);
	p5.add(addEnteredButton);
	add(p5, LEFT_ALIGNMENT);

	p6 = new Panel();
	totalTimeLabel = new Label("Total time: " + totalHours);
	p6.add(totalTimeLabel);
	add(p6, CENTER_ALIGNMENT);

	p7 = new Panel();
	clearButton = new Button("Clear");
	clearButton.addActionListener(this);
	p7.add(clearButton);
	add(p7, LEFT_ALIGNMENT);

	resize(450, 250);

	calc = new TimeCalculator();
    }

    // defining actionPerformed
    public void actionPerformed(ActionEvent e) {
	Object source = e.getSource();

	if (source == computeTimeButton) {
	    int hr1, hr2;
	    Boolean pm1, pm2;
	    String time1;
	    String time2;

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

	    time1 = hr1 + ":" + minutes1.getSelectedItem() + ":00";
	    time2 = hr2 + ":" + minutes2.getSelectedItem() + ":00";

	    beginTime = Time.valueOf(time1);
	    endTime = Time.valueOf(time2);

	    timeElapsedHours = calc.timePassed(beginTime, endTime);

	}

	if (source == addElapsedButton) {
	    totalHours = calc.addTime(timeElapsedHours, totalHours);
	    totalListed.append(timeElapsedHours + "+");
	}

	if (source == addEnteredButton) {
	    enterTimeHours.setHours(Integer.parseInt(hours3.getSelectedItem()));
	    enterTimeHours.setMinutes(Integer.parseInt(minutes3.getSelectedItem()));
	    totalHours = calc.addTime(enterTimeHours, totalHours);
	    totalListed.append(enterTimeHours + "+");
	}

	if (source == clearButton) {
	    totalHours.clear();
	    timeElapsedHours.clear();
	    totalListed.setLength(0);
	}

	repaint();
    }

    public void paint(Graphics g) {
	timeElapsedLabel.setText("Elapsed time: " + timeElapsedHours);
	totalTimeLabel.setText("Total time: " + totalHours);
	this.showStatus(totalListed.toString());
    }

}
