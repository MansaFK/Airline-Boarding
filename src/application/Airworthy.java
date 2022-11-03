package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

public class Airworthy{

	private PriorityQueue<Passenger> oldPQ = new PriorityQueue<Passenger>(Collections.reverseOrder());
	private PriorityQueue<Passenger> newPQ = new PriorityQueue<Passenger>(Collections.reverseOrder());
	FileWriter fileWriter;
	
	public Airworthy() {

	}
	
	public Airworthy(String file) throws IOException {

		String airworthy70 = "C:/Users/Kaman/eclipse-workspace/Airline Boarding/src/application/Airworthy70.txt/";
		String airworthy85 = "C:/Users/Kaman/eclipse-workspace/Airline Boarding/src/application/Airworthy85.txt/";
		String airworthy100 = "C:/Users/Kaman/eclipse-workspace/Airline Boarding/src/application/Airworthy100.txt/";
		String result70 = "results70.txt";
		String result85 = "results85.txt";
		String result100 = "results100.txt";
		
		if(file.equals(airworthy70)) {
			fileWriter = new FileWriter(result70);
			System.out.println("Printing 70");
		}else if (file.equals(airworthy85)) {
			fileWriter = new FileWriter(result85);
			System.out.println("Printing 85");
		}else if(file.equals(airworthy100)){
			fileWriter = new FileWriter(result100);
			System.out.println("Printing 100");
		}else {
			System.out.println("Some other file.");
		}
		readInputFile(file);
	}

	public void newProcedure(PriorityQueue<Passenger> pq) throws IOException{
		
		int[] rowNumbers = new int[pq.size()];
		int j = 0;
		
		String labels = String.format("%-15s%-5s%-5s%-5s\n", "Name", "Type", "Row", "Key");
		writeToFile(labels);
		for(Passenger passenger: pq) {
			writeToFile(passenger.toString());
			rowNumbers[j] = passenger.getRow();
			j++;
		}
		newBoardingTime(rowNumbers);
	}
	
	public void oldProcedure(PriorityQueue<Passenger> pq) throws IOException {
		int[] rowNumbers = new int[pq.size()];
		int i = 0;
		
		String labels = String.format("%-15s%-5s%-5s%-5s\n", "Name", "Type", "Row", "Key");
		writeToFile(labels);
		for(Passenger passenger: pq) {
			writeToFile(passenger.toString());	
			rowNumbers[i] = passenger.getRow();//Filling array of row seats for the time method
			i++;
		}
		oldBoardingTime(rowNumbers);
	}

	public void oldBoardingTime(int[] rowNumbers) throws IOException{
		int time = 0;
		int lastIndex = rowNumbers.length-1;
		for(int i = 0; i <= lastIndex; i++) {
			if(i == 0) {
				time += 1;
			}
			else if(rowNumbers[i] >= rowNumbers[i-1]) {
				time += 25;
			}
			else {
				time += 1;
			}
		}
		int minutes = time / 60;
		int seconds = time % 60;
		
		writeToFile("\nOld boarding time is "+minutes+" minutes and "+seconds+ " seconds\n\n");
		
	}

	public void newBoardingTime(int[] rowNumbers) throws IOException {
		int time = 0;
		int lastIndex = rowNumbers.length-1;
		for(int i = 0; i <= lastIndex; i++) {
			if(i == 0) {
				time += 1;
			}
			else if(rowNumbers[i] >= rowNumbers[i-1]) {
				time += 25;
			}
			else {
				time += 1;
			}
		}
		int minutes = time / 60;
		int seconds = time % 60;
		
		writeToFile("\nNew boarding time is "+minutes+" minutes and "+seconds+ " seconds\n\n");
		fileWriter.close();
	}

	public void readInputFile(String aFile) throws IOException {
		String file = aFile;
		Scanner scanner = new Scanner(new File(file));
		ArrayList<Passenger> oldPassengerList = new ArrayList<Passenger>();
		//Create another list to pass to the new boarding procedure
		ArrayList<Passenger> newPassengerList = new ArrayList<Passenger>();
		while (scanner.hasNextLine()) {
			String nextLine = scanner.nextLine();
			String[] tokens = nextLine.split(" ");

			// demonstration of printing token items
			Passenger curPassenger = new Passenger();
			
			for (int i = 0; i < tokens.length; i++) {

				if (i == 0) {
					curPassenger.setName(tokens[i]);
				} else if (i == 1) {
					curPassenger.setType(tokens[i]);
				} else if (i == 2) {
					curPassenger.setRow(Integer.parseInt(tokens[i]));
				} else {
					throw new RuntimeException("Some Error");
				}
			}
			oldPassengerList.add(curPassenger);
		}
		scanner.close();
		
		for(int i = 0; i < oldPassengerList.size(); i++) {
			Passenger passenger = oldPassengerList.get(i).copy();
			newPassengerList.add(passenger);
		}
		loadOldPQ(oldPassengerList);
		loadNewPQ(newPassengerList);
	}

	public void loadOldPQ(ArrayList<Passenger> list) throws IOException {
		
		int priorityNumber = list.size();
		
		ArrayList<Passenger> firstGroup = new ArrayList<Passenger>();
		ArrayList<Passenger> secondGroup = new ArrayList<Passenger>();
		ArrayList<Passenger> thirdGroup = new ArrayList<Passenger>();
		ArrayList<Passenger> fourthGroup = new ArrayList<Passenger>();
		ArrayList<Passenger> fifthGroup = new ArrayList<Passenger>();
		ArrayList<Passenger> sixthGroup = new ArrayList<Passenger>();
		ArrayList<Passenger> seventhGroup = new ArrayList<Passenger>();
		ArrayList<Passenger> eighthGroup = new ArrayList<Passenger>();
		
		ArrayList<ArrayList<Passenger>> passengerLists = new ArrayList<ArrayList<Passenger>>();
		Collections.addAll(passengerLists, firstGroup, secondGroup, thirdGroup, fourthGroup,
							fifthGroup, sixthGroup, seventhGroup, eighthGroup);
		
		Iterator<Passenger> iter = list.iterator();
		
		while(iter.hasNext()) {
			Passenger passenger = iter.next();
			
			if(passenger.getType().equals("H")) {
				firstGroup.add(passenger);
			}
			else if(passenger.getRow() >= 1 && passenger.getRow() <= 4 
					&& passenger.getType().equals("E")){
				secondGroup.add(passenger);
			}else if(passenger.getRow() >= 1 && passenger.getRow() <= 4) {
				thirdGroup.add(passenger);
			}else if(passenger.getType().equalsIgnoreCase("E") || passenger.getRow() == 10
					|| passenger.getRow() == 11) {
				fourthGroup.add(passenger);
			}else if(passenger.getRow() >= 23 && passenger.getRow() <= 26) {
				fifthGroup.add(passenger);
			}else if(passenger.getRow() >= 17 && passenger.getRow() <= 22) {
				sixthGroup.add(passenger);
			}else if(passenger.getRow() >= 11 && passenger.getRow() <= 16) {
				seventhGroup.add(passenger);
			}else if(passenger.getRow() >= 5 && passenger.getRow() <= 10) {
				eighthGroup.add(passenger);
			}else {
				throw new RuntimeException("Some error");
			}	
		}
		
		for(ArrayList<Passenger> aList: passengerLists) {
			for(Passenger passenger: aList) {
				passenger.setKey(priorityNumber);
				oldPQ.add(passenger);
				priorityNumber--;
			}
		}
		oldProcedure(oldPQ);
	}

	public void loadNewPQ(ArrayList<Passenger> list) throws IOException {
		
		int priorityNumber = list.size();
		ArrayList<Passenger> firstGroup = new ArrayList<Passenger>();
		ArrayList<Passenger> secondGroup = new ArrayList<Passenger>();
		ArrayList<Passenger> thirdGroup = new ArrayList<Passenger>();
		ArrayList<Passenger> fourthGroup = new ArrayList<Passenger>();
		ArrayList<Passenger> fifthGroup = new ArrayList<Passenger>();
		
		ArrayList<ArrayList<Passenger>> passengerLists = new ArrayList<ArrayList<Passenger>>();	
		Collections.addAll(passengerLists, firstGroup, secondGroup, thirdGroup, fourthGroup, fifthGroup);	
		
		Iterator<Passenger> iter = list.iterator();
		
		while(iter.hasNext()) {
			Passenger passenger = iter.next();
			
			if(passenger.getType().equals("H")) {
				firstGroup.add(passenger);
			}
			else if(passenger.getRow() >= 1 && passenger.getRow() <= 4 
					&& passenger.getType().equals("E")){
				secondGroup.add(passenger);
			}else if(passenger.getRow() >= 1 && passenger.getRow() <= 4) {
				thirdGroup.add(passenger);
			}else if(passenger.getType().equals("E") || passenger.getRow() == 10
					|| passenger.getRow() == 11) {
				fourthGroup.add(passenger);
			}else if (passenger.getType().equals("G")){
				fifthGroup.add(passenger);
			}else {
				throw new RuntimeException("Some error.");
			}
		}
		
		for(ArrayList<Passenger> aList: passengerLists) {
			for(Passenger passenger: aList) {
				passenger.setKey(priorityNumber);
				newPQ.add(passenger);
				priorityNumber--;
			}
		}
		newProcedure(newPQ);
	}
	//Method writes to file
	public void writeToFile(String aString) throws IOException{
		
		StringWriter stringWriter = new StringWriter();
		String content;
		String newLine = System.getProperty("line.separator");
		stringWriter.write(aString);
		stringWriter.write(newLine);
		content = stringWriter.toString();
		fileWriter.write(content);
	}
	
	public void runSimulation() throws IOException {
		
		String airworthy70 = "C:/Users/Kaman/eclipse-workspace/Airline Boarding/src/application/Airworthy70.txt/";
		String airworthy85 = "C:/Users/Kaman/eclipse-workspace/Airline Boarding/src/application/Airworthy85.txt/";
		String airworthy100 = "C:/Users/Kaman/eclipse-workspace/Airline Boarding/src/application/Airworthy100.txt/";
		
		Airworthy flight70 = new Airworthy(airworthy70);
		Airworthy flight85 = new Airworthy(airworthy85);
		Airworthy flight100 = new Airworthy(airworthy100);
	}
}
