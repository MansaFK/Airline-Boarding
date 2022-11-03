package application;

public class Passenger implements Comparable<Passenger>{

	private int key;
	private int row;
	private String name;
	private String type;

	public Passenger() {

	}

	public Passenger(int key, int row, String name, String type) {
		this.key = key;
		this.row = row;
		this.name = name;
		this.type = type;
	}
	
	//The custom copy constructor
	public Passenger(Passenger passenger) {
		this.key = passenger.key;
		this.row = passenger.row;
		this.name = passenger.name;
		this.type = passenger.type;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int compareTo(Passenger o) {
		if(this.key > o.key) {
			return 1;
		}
		else if(this.key < o.key){
			return -1;
		}
		else {
			return 0;
		}
	}
	//copy method
	public Passenger copy() {
		
		return new Passenger(this);
	}
	
	
	
	@Override
	public String toString(){
		String passengerString = String.format("%-15s%-5s%-5d%-5d", name, type, row, key);
		return passengerString;
	}
}
