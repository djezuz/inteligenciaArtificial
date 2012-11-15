package domains;

import exceptions.DoorOpenedException;

public class Elevator {

	public static final int MIN_FLOOR = -2;
	public static final int MAX_FLOOR = 10;
	
	private int floor;
	private boolean doorClosed;
	
	//por default - Piso 0 y puerta cerrada.
	public Elevator() {
		floor = 0;
		doorClosed = true;
	}
	
	public void buttonUp() throws DoorOpenedException{
		checkDoorClosed();
		
		if (floor<MAX_FLOOR)
			floor++;
	}
	public void buttonDown() throws DoorOpenedException{
		checkDoorClosed();
		
		if (floor>MIN_FLOOR)
			floor--;
	}
	private void checkDoorClosed() throws DoorOpenedException{
		if (!doorClosed){
			throw new DoorOpenedException("La puerta no esta cerrada. Status: doorClosed="+doorClosed+"; floor="+floor);
		}
	}
	public void closeDoor(){
		doorClosed = true;
	}
	public void openDoor(){
		doorClosed = false;
	}
	
	public int getFloor() {
		return floor;
	}
	public boolean isDoorClosed() {
		return doorClosed;
	}

	public boolean isFloorValid(int floorWanted) {
		
		return floorWanted>=MIN_FLOOR && floorWanted <= MAX_FLOOR;
	}
}
