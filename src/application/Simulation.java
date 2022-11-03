package application;

import java.io.IOException;


public class Simulation {

	public Simulation() {
		
	}
	public static void main(String[] args) throws IOException {
		
		/*Airworthy plane70 = new Airworthy();
		String plane70 = "C:/Users/Kaman/eclipse-workspace/Airline Boarding/src/application/Airworthy70.txt/";
		plane85 = "C:/Users/Kaman/eclipse-workspace/Airline Boarding/src/application/Airworthy85.txt/";
		plane100 = "C:/Users/Kaman/eclipse-workspace/Airline Boarding/src/application/Airworthy100.txt/";
		plane70.readInputFile(plane);*/
		
		// new Airworthy("C:/Users/Kaman/eclipse-workspace/Airline Boarding/src/application/Airworthy70.txt/");
		//Airworthy flight70 = new Airworthy("C:/Users/Kaman/eclipse-workspace/Airline Boarding/src/application/Airworthy70.txt/");
		Airworthy planes = new Airworthy();
		planes.runSimulation();
		
	}

}
 