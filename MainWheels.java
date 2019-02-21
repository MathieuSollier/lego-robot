import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.utility.Delay;

import java.awt.List;
import java.lang.reflect.Array;
import java.util.ArrayList;

import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;
import lejos.utility.PilotProps;


public class MainWheels {
		

	//MonThread t ; (a regarder pour temps morts)
	private RegulatedMotor pinceMotor;
	private RegulatedMotor rightMotor;
	private RegulatedMotor leftMotor;
	private Wheel roue1;
	private Wheel roue2;
	private WheeledChassis chassis;
	private static int acceleration = 800;
	private static int vitesseLente = 70 ;
	private static int vitesseMoyenne = 110;
	private static int vitesseRapide = 9999;
	MovePilot p;

	public MainWheels(){
		//MonThread t = new MonThread();
		//pinceMotor = new EV3MediumRegulatedMotor(MotorPort.A);
		rightMotor = new EV3LargeRegulatedMotor(MotorPort.A);
		leftMotor = new EV3LargeRegulatedMotor(MotorPort.C);	
		roue1 = WheeledChassis.modelWheel (rightMotor, 56) .offset (70);
		roue2 = WheeledChassis.modelWheel (leftMotor,56) .offset (-70);
		chassis = new WheeledChassis(new Wheel[] { roue1, roue2 }, WheeledChassis.TYPE_DIFFERENTIAL);
		this.p = new MovePilot(chassis);
		p.setLinearAcceleration(acceleration);


	}

	public void avancer(long period) {
		p.forward();
		Delay.msDelay(period);
	}

	public void reculer(long period) {
		p.setLinearSpeed(vitesseRapide);
		p.backward();
		Delay.msDelay(period);
	}

	public void gauche(double angle) {
		//rightMotor.backward();
		p.arc(0, -angle);
		
	}


	public void droite(double angle) {
		//rightMotor.forward();
		p.arc(0, angle);
	}
	public void stop() {
		p.stop();
	}


	public int getVitesseLente() {
		return vitesseLente;
	}

	public int getVitesseMoyenne() {
		return vitesseMoyenne;
	}

	public int getVitesseRapide() {
		return vitesseRapide;
	}



	
	
}











