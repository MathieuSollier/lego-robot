/*
 * Copyright © 2018 HUANG Yidong. All rights reserved.
 * Contact: adrienhuang@icoud.com
 * This is the code for a school project of the course "Intelligence Artificielle"
 * This code is used to control a EV3 robot made by The LEGO Group
 */


import lejos.hardware.*;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.utility.Delay;


/**
 * This class is made for controlling and knowing the currant status of the robot's claws.
 * All the packages are imported from LeJos, see the documentation at https://lejos.sourceforge.io/ev3/docs/
 * See all the details in the code's comments
 * @author HUANG Yidong
 */


public class Claws {

	/**
	 * This class contain 3 parameters:
	 * -A EV3MediumRegulatedMotor type links to the claws' motor
	 * -A boolean type variable "isClosed" to store claws' current status(open or not)
	 * -An int type variable "SPEED" to store the constant value of the motor's speed(here set to 50000 to achieve the maximum speed of the motor)
	 */
	private EV3MediumRegulatedMotor claws_motor;
	private boolean isClosed;
	private static final float SPEED=50000;
	
	/**
	 * In the constructor there is only one parameter: the motor port to initialise the EV3MediumRegulatedMotor class.
	 * The boolean "isClosed" will be set as true when initialising.
	 * The motor speed will be set to the "SPEED" value using the setSpeed in the EV3MediumRegulatedMotor class.
	 */
	Claws(Port claws_motor_port){
		claws_motor = new EV3MediumRegulatedMotor(claws_motor_port);
		isClosed=true;
		claws_motor.setSpeed(SPEED);
	}

	/**
	 * This action uses the rotate function in the EV3MediumRegulatedMotor class to close the claws than set the isClosed boolean true.
	 */
	public void close() {
		claws_motor.rotate(-820);
		//Delay.msDelay(500);
		isClosed=true;
	}

	/**
	 * This action uses the rotate function in the EV3MediumRegulatedMotor class to open the claws than set the isClosed boolean false.
	 */
	public void open() {
		claws_motor.rotate(820, true);
		//Delay.msDelay(500);
		isClosed=false;
	}

	/** This function returns the current claws status.
	 * @return the boolaen represent the the current claws status
	 */
	public boolean isClosed() {
		return isClosed;
	}

}
