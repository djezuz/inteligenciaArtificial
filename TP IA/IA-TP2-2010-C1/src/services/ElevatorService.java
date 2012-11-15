package services;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import domains.Elevator;
import exceptions.DoorOpenedException;
import exceptions.FloorNotValidException;

public class ElevatorService {

	private Elevator elevator;
	
	public ElevatorService() {
		elevator = new Elevator();
	}
	
	
	public void goToFloor(int floorWanted) throws FloorNotValidException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if (!elevator.isFloorValid(floorWanted)){
			throw new FloorNotValidException("El piso seleccionado ("+floorWanted+") no es valido: Lowest="+Elevator.MIN_FLOOR+"; Highest:"+Elevator.MAX_FLOOR);
		}
		Method direction;
		if (floorWanted>elevator.getFloor())
			direction = this.getClass().getMethod("up1Floor");
		else 
			direction = this.getClass().getMethod("down1Floor");
		
		while (elevator.getFloor() != floorWanted){
			direction.invoke(this,(Object [])null);
		}
		elevator.openDoor();
	}
	
	public void up1Floor() throws DoorOpenedException{
		checkDoorClosed();
		elevator.buttonUp();
	}
	public void down1Floor() throws DoorOpenedException{
		checkDoorClosed();
		elevator.buttonDown();
	}
	
	private void checkDoorClosed(){
		if (!elevator.isDoorClosed()){
			System.out.println("Door is opened, closing...");
			elevator.closeDoor();
		}
	}
	
	public String getStatus(){
		return "Elevator floor: "+elevator.getFloor()+". Door "+(elevator.isDoorClosed()?"closed.":"opened.");
	}
}
