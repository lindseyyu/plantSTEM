/***
 * Plant Processing program
 * -take in data through csv
 * -process data with algorithm
 * 
 * File Reader and Writer from Gordie Campbell
 * -source: http://compsci.dalton.org/~gordie/StudentN.java
 * 
 * 
 * 
 * @author Kyle Chu
 * @author Lindsey Yu
 */
 
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import javax.swing.text.html.HTMLDocument.Iterator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;
import java.io.FileWriter;
 
public class PlantProcessing {
	static int k = 0;
	public static void main(String[] args) throws IOException {
		ArrayList<PlantData> listofdata = new ArrayList<PlantData>();
 
		BufferedReader br = null;
 
		try {
 
			String sCurrentLine;
			
			//file writer
			
 
			br = new BufferedReader(new FileReader(new File("/Users/kylechu/Desktop/testSeven.csv")));
			br.readLine();
 
			while ((sCurrentLine = br.readLine()) != null) {
 
				String record = sCurrentLine;
				//System.out.println(record);
 
				String [] actualdata = record.split(",");
				PlantData s = new PlantData();
				s.setLight(Double.parseDouble(actualdata[1]));
				s.setTemperature(Double.parseDouble(actualdata[2]));
				s.setHumidity(Double.parseDouble(actualdata[3]));
				s.setMoisture(Double.parseDouble(actualdata[4]));
				s.setPh(Double.parseDouble(actualdata[5]));
				listofdata.add(s);
				//System.out.println(sCurrentLine);
			}//while
 
			//System.out.println(listofdata.size());
		}//try
 
		catch(Exception e)
		{
 
		}
		//scanner.close();
		
		//HASHMAP????
		HashMap<String, double[]> plants = new HashMap<String, double[]>();
 
        plants.put("Carrot", new double[] {4.8,5.5,6,8,40,70,98,100,50,85});
        plants.put("Potato", new double[] {6.0,6.8,6,6,60,65,95,98,40,40});
		plants.put("Tomato", new double[] {6.0,6.8,8,10,40,70,90,95,75,90});
		plants.put("Cucumber", new double [] {5.1,5.7,8,10,40,70,95,95,65,90});
		plants.put("Pumpkin", new double [] {5.8,6.8,6,6,40,70,50,70,70,105});
		plants.put("Lettuce", new double [] {6.2,6.8,10,12,40,70,95,100,50,70});
		plants.put("Summer squash", new double [] {5.8,6.8,8,10,40,70,50,70,70,105});
		plants.put("Bell pepper", new double [] {4.7,5.5,6,6,40,70,95,100,70,95});
		plants.put("Melon" , new double [] {6.5,7.5,8,10,40,70,85,90,65,90});
		plants.put("Artichoke" , new double [] {6.5,8,8,8,40,70,95,100,70,80});
		plants.put("Broccoli", new double [] {6.0,7.5,6,6,40,70,95,100,45,85});
		plants.put("Cauliflower", new double [] {6.0,7.5,6,6,40,70,95,98,45,85});
		plants.put("Corn", new double [] {6.0,6.8,8,10,40,70,95,98,65,85});
		plants.put("Spinach", new double [] {6.5,7.5,4,6,40,70,95,98,40,75});
		plants.put("Blueberry", new double [] {5.5,6.5,7,8,40,70,90,95,32,45});
		plants.put("Cranberry", new double [] {5.5,6.5,7,8,40,70,90,95,32,45});
		plants.put("Cabbage", new double [] {6.0,7.5,6,8,40,70,95,100,40,85});
		plants.put("Hibiscus", new double [] {5.5,7.0,6,6,40,40,50,60,65,75});
		plants.put("Tiger lily", new double [] {5.5,6.5,6,8,40,40,50,60,45,60});
		plants.put("Hydrangea (Pink)", new double [] {6.0,7.0,5,7,40,40,50,60,40,70});
		plants.put("Hydrangea (Blue)", new double [] {4.5,5.5,5,7,40,40,50,60,40,70});
		//HASHMAP END
		
		//pH1, pH2, Sun1, Sun2, Moist1, Moist2, Humid1, Humid2, Temp1, Temp2
		FileWriter writer = new FileWriter("/Users/kylechu/Desktop/Win.txt");
		//call functions
		double[] average = Average(listofdata);
		Simple[] processed = Algorithm(average, plants);
		Kyle(processed, writer);
		writer.close();
		
		
		//check averages
		//System.out.println(Arrays.toString(Average(listofdata)));
		
		
 
 
	}//main
	
 
	public static double[] Average(ArrayList<PlantData> listofdata){
		double light = 0;
		double temperature = 0;
		double humidity = 0;
		double moisture = 0;
		double ph = 0;
		int size = listofdata.size();
 
		//Add everything up
		for (int  i = 0; i < listofdata.size(); i++) {
			light = listofdata.get(i).getLight() + light;
			temperature = listofdata.get(i).getTemperature() + temperature;
			humidity = listofdata.get(i).getHumidity() + humidity;
			moisture = listofdata.get(i).getMoisture() + moisture;
			ph = listofdata.get(i).getPh() + ph;
		}
 
		//Divide by size of array list to get final average
		light = light;			//no average, keep in minutes
		temperature = temperature/size;
		humidity = humidity/size;
		moisture = moisture/size;
		ph = ph/size;
 
		//declare pre-set arrays
		if(light == 0) light = 0.1;
		if(temperature == 0) temperature = 0.1;
		if(humidity == 0) humidity = 0.1;
		if(moisture == 0) moisture = 0.1;
		if(ph == 0) ph = 0.1;
 
 
		double[] average = new double[5];
		average[0] = light;
		average[1] = temperature;
		average[2] = humidity;
		average[3] = moisture;
		average[4] = ph;
 
		return average;
 
	}
 
 
	public static Simple[] Algorithm(double[] average, HashMap<String, double[]> map){
 
		
		double avLight = average[0];
		double avTemp = average[1];
		double avHumidity = average[2];
		double avMoisture = average[3];
		double avPh = average[4];
 
		//Sun is in hours
 
		double phScore = 0;
		double lightScore = 0;
		double moistureScore = 0;
		double humidityScore = 0;
		double tempScore = 0;
		double plantScore = 0;
		//String[] names = new String[map.size()];
		Simple[] SimpArr = new Simple[21];
 
		//pH1, pH2, Sun1, Sun2, Moist1, Moist2, Humid1, Humid2, Temp1, Temp2
 
		//do the stuff 
		for(HashMap.Entry<String, double[]> entry : map.entrySet()){
			double[] plant = new double[10];
			plant = entry.getValue();
			//System.out.println(Arrays.toString(entry.getValue()));
			//ph
			if(avPh > plant[0] && avPh < plant[1]) phScore = 0;
			else if(avPh < plant[0]) {
				phScore = Math.abs(((avPh - plant[0])/plant[0]));
			}
			else if(avPh > plant[1]){
				phScore = Math.abs(((avPh - plant[1])/plant[1]));
			}
			//light 	MUST CONVERT TO MINUTES [X]
			if(avLight > plant[2]*60 && avLight < plant[3]*60) lightScore = 0;
			else if(avLight < (plant[2]*60)) {
				lightScore = Math.abs(((avLight - (plant[2]*60))/(plant[2]*60)));
			}
			else if(avLight > (plant[3]*60)){
				lightScore = Math.abs(((avLight - (plant[3]*60))/(plant[3]*60)));
			}
			
			//moisture 		MUST CONVERT ARDUINO READINGS TO PERCENT [ ]
			if(avMoisture > plant[4] && avMoisture < plant[5]) moistureScore = 0;
			else if(avMoisture < plant[4]) {
				moistureScore = Math.abs(((avMoisture - plant[4])/plant[4]));
			}
			else if(avMoisture > plant[5]){
				moistureScore = Math.abs(((avMoisture - plant[5])/plant[5]));
			}
			
			//Humidity
			if(avHumidity > plant[6] && avHumidity < plant[7]) humidityScore = 0;
			else if(avHumidity < plant[6]) {
				humidityScore = Math.abs(((avHumidity - plant[6])/plant[6]));
			}
			else if(avHumidity > plant[7]){
				humidityScore = Math.abs(((avHumidity - plant[7])/plant[7]));
			}
			
			//Temperature
			if(avTemp > plant[8] && avTemp < plant[9]) tempScore = 0;
			else if(avTemp < plant[8]) {
				tempScore = Math.abs(((avTemp - plant[8])/plant[8]));
			}
			else if(avTemp > plant[9]){
				tempScore = Math.abs(((avTemp - plant[9])/plant[9]));
			}
			
			//Overall difference
			plantScore = Math.abs(((1+(5*lightScore))*(1+(4*moistureScore))*(1+(3*humidityScore))*(1+(2*phScore))*(1+tempScore))-1);
			DecimalFormat df = new DecimalFormat("#.##");
			plantScore = Double.parseDouble(df.format(plantScore));
			
//			System.out.println(lightScore);
//			System.out.println(phScore);
//			System.out.println(tempScore);
//			System.out.println(moistureScore);
//			System.out.println(humidityScore);
			//put the final averages data into another array
 
			//System.out.print(entry.getKey() + ":");
			//System.out.println(" " + df.format(plantScore) + "%");
 
			SimpArr[k] = new Simple(entry.getKey(), plantScore);
			SimpArr[k].setName(entry.getKey());
			SimpArr[k].setValue(plantScore);
			
			
				
				//System.out.println(SimpArr[k].getName());
				//System.out.println(SimpArr[k].getValue());	
			
			//System.out.println(SimpArr[0]);
			//System.out.println(Arrays.toString(SimpArr));
			k++;
			
		}//Loop the Map
		
		
	//	Arrays.sort(total);
	//System.out.println(Arrays.toString(total));
		
		return SimpArr;
	}//Algorithm
	
	public static void Kyle(Simple [] input, FileWriter out) throws IOException{
		Arrays.sort(input);
		out.write("Recommended Plants Ranked by Percent Difference from your Soil Conditions:" + "\n");
		out.write("__________________________________________________________________________" + "\n");
		for (int i = 0; i < input.length; i++) {
			//System.out.print(input[i].getValue() + "%");
			//System.out.println(" " + input[i].getName());	
			out.write(input[i].getValue() + "% " + input[i].getName() + "\n");
			out.flush();
		}
		out.write("__________________________________________________________________________");
		out.close();
		
	}
	
 
 
}//pgm
 
