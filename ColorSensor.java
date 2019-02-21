import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import lejos.robotics.filter.MeanFilter;

/**
 * This class is made for controlling and knowing the currant status of the robot's claws.
 * All the packages are imported from LeJos, see the documentation at https://lejos.sourceforge.io/ev3/docs/
 * See all the details in the code's comments
 * @author Safé Chikh Hamouda
 */

public class ColorSensor {

    /**
     * This class contain 10 parameters:
     * -A EV3ColorSensor type to access the color sensor
     * -A SampleProvider type to take samples from EV3ColorSensor
     * -A table of float to store the sample
     * -7 float tables to store the color prototypes.
     */
    private static SampleProvider sp ;
    private static EV3ColorSensor colorSensor;
    private float[] sample;
    private static final float [][] green = {{(float)0.030392157,(float)0.05392157,(float)0.03627451},{(float)0.033333335,(float)0.05490196,(float)0.038235296},{(float)0.034313727,(float)0.056862745,(float)0.04117647}};
    private static final float [][] red = {{(float)0.08627451,(float)0.016666668,(float)0.024509804},{(float)0.083333336,(float)0.015686275,(float)0.02254902},{(float)0.08137255,(float)0.016666668,(float)0.023529412}};
    private static final float [][] blue ={{(float)0.011764706,(float)0.018627452,(float)0.05882353},{(float)0.011764706,(float)0.018627452,(float)0.060784314},{(float)0.012745098,(float)0.01764706,(float)0.05882353}};
    private static final float [][] black ={{(float)0.014705882,(float)0.010784314,(float)0.02745098},{(float)0.014705882,(float)0.01372549,(float)0.025490196},{(float)0.015686275,(float)0.014705882,(float)0.026470589}};
    private static final float [][] gray = {{(float)0.055882353,(float)0.051960785,(float)0.08431373},{(float)0.05490196,(float)0.050980393,(float)0.08137255},{(float)0.056862745,(float)0.05392157,(float)0.087254904}};
    private static final float [][] white = {{(float)0.03137255,(float)0.01372549,(float)0.023529412},{(float)0.16862746,(float)0.14803922,(float)0.20294118},{(float)0.17450981,(float)0.15294118,(float)0.20980392}};
    private static final float [][] yellow ={{(float)0.14705883,(float)0.11372549,(float)0.048039217},{(float)0.15784314,(float)0.120588236,(float)0.04901961},{(float)0.1627451,(float)0.12941177,(float)0.050980393}};


    /**
     *
     * @param port The port corresponds to the color sensor
     */
    public ColorSensor(Port port){
        colorSensor = new EV3ColorSensor(port); 
        sp = colorSensor.getRGBMode();
        colorSensor.setFloodlight(Color.WHITE);
        sample = new float[sp.sampleSize()];
        sp.fetchSample(sample, 0);
    }


    /**
     * This fonction will take a sample and than compares to the color prototypes to find the closest match
     * @return a String contain the color information
     */
    public String  currentedColor(){
         

        
         sp.fetchSample(sample, 0);

         
         
         double minscal = Double.MAX_VALUE;
            
         String color = "error";
			
			
         double scalaire =scalaire(sample, blue);

         if (scalaire < minscal) {
             minscal = scalaire;
             color = "blue";
				
         }
			
         scalaire = scalaire(sample, red);
			
         if (scalaire < minscal) {
             minscal = scalaire;
             color =  "red";
         }
			
         scalaire = scalaire(sample, green);
		
         if (scalaire < minscal) {
             minscal = scalaire;
             color =  "green";
         }
			
         scalaire = scalaire(sample, black);

         if (scalaire < minscal) {
             minscal = scalaire;
             color =   "black";
         }

         scalaire = scalaire(sample, yellow);
	        
         if (scalaire < minscal) {
             minscal = scalaire;
             color =  "yellow";
         }
			
         scalaire = scalaire(sample,white);

         if (scalaire < minscal) {
             minscal = scalaire;
             color = "white";
         }
		  
         scalaire = scalaire(sample, gray);
			        
         if (scalaire < minscal) {
             minscal = scalaire;
             color = "gray";
         }
	
         
         
        return color;
    }


    /**
     * This function verify if the color is white
     * @return true is the color is white
     */
    public boolean isWhite() {
    	return currentedColor().equals("white");
    }


    /**
     * This function calculate the distance between the sample and a color prototype
     * @param v1 the sample
     * @param v2 the color prototype
     * @return the distance between the two
     */
    private static double scalaire(float[] v1, float[][] v2) {
		float sum=0;
    	for(int i=0;i<v2.length;i++) {
			sum+=(Math.sqrt (Math.pow(v1[0] - v2[i][0], 2.0) +
				Math.pow(v1[1] - v2[i][1], 2.0) +
				Math.pow(v1[2] - v2[i][2], 2.0)));
    	}
    	return sum/v2.length;
	}
 }
    
  
         