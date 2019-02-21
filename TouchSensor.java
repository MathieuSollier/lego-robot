/*
 * Copyright © 2018 HUANG Yidong. All rights reserved.
 * Contact: adrienhuang@icoud.com
 * This is the code for a school project of the course "Intelligence Artificielle"
 * This code is used to control a EV3 robot made by The LEGO Group

 */

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.utility.Delay;

/**
 * This class is made for controlling and knowing the currant status of the robot's claws.
 * All the packages are imported from LeJos, see the documentation at https://lejos.sourceforge.io/ev3/docs/
 * See all the details in the code's comments
 * @author HUANG Yidong
 */

public class TouchSensor{

	/**
	 * This class reads the touch sensor data
	 */
	private EV3TouchSensor touch;

	/**
	 * The constructor has one
	 * @param p the port links to the touch sensor
	 */
	public TouchSensor(Port p) {
		touch =new EV3TouchSensor(p);
	}


	/**
	 * This function allows the robot read the touch sensor data
	 * @return true if the touch sensor is touched
	 */
	public boolean isTouched(){
		SensorMode touchMode = touch.getTouchMode();
	    float [] sample = new float[touch.sampleSize()];
	    int iteration_threshold = 30;
        for(int i = 0; i < iteration_threshold; i++) {
        	touch.fetchSample(sample, 0);
        	if(sample[0]!=0)
        		return true;
        }
	    return false;
	}

}
