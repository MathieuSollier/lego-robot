/*
 * Copyright © 2018 HUANG Yidong. All rights reserved.
 * Contact: adrienhuang@icoud.com
 * This is the code for a school project of the course "Intelligence Artificielle"
 * This code is used to control a EV3 robot made by The LEGO Group

 */

import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

/**
 * This class is made for controlling and knowing the currant status of the robot's claws.
 * All the packages are imported from LeJos, see the documentation at https://lejos.sourceforge.io/ev3/docs/
 * See all the details in the code's comments
 * @author HUANG Yidong
 */

public class Ultrasonic {
	/**
	 * This class allows the robot read the distance data and contains 2 parameters.
	 * -A EV3UltrasonicSensor type
	 * -A float type to store the distance data
	 */
	private EV3UltrasonicSensor us;
	private float currentDistance;


	/**
	 * This constructor uses the port connected to the sensor to initialise.
	 * @param p port connected to the sensor
	 */
	public Ultrasonic(Port p) {
		us=new EV3UltrasonicSensor(p);
		currentDistance=this.getDistance();
	}


	/**
	 * This function allows the robot to read the distance from ultrasonic sensor
	 * @return the distance in float
	 */
	public float getDistance() {
		
		SampleProvider sp = us.getDistanceMode();
		float values []= new float[10];
		float valuesCorrige []= new float[8];
		int iteration_threshold = 10;
        for(int i = 0; i < iteration_threshold; i++) {
        	float [] sample = new float[sp.sampleSize()];
            sp.fetchSample(sample, 0);
            //return sample[0];
            values[i]=sample[0];
        }
        int minidx1=0, maxidx1=0,minidx2=1,maxidx2=1;
        float minValue1=values[0],maxValue1=values[0],minValue2=values[1],maxValue2=values[1];
        for(int i = 0; i < iteration_threshold; i++) {
        	if(minValue1>values[i]) {
        		minValue2=minValue1;
        		minValue1=values[i];
        		minidx2=minidx1;
        		minidx1=i;
        	}
        	if(maxValue1<values[i]) {
        		maxValue2=maxValue1;
        		maxValue1=values[i];
        		maxidx2=maxidx1;
        		maxidx1=i;
        	}        		
        }
        
        		
        values[minidx1]=0;
        values[minidx2]=0;
        values[maxidx1]=0;
        values[maxidx2]=0;
        
        float sum=0;
        for(int i = 0; i < iteration_threshold; i++) {
        	sum+=values[i];
        }
        
        return sum/6;
	}

	
}

