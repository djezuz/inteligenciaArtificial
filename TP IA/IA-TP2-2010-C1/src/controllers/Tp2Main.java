package controllers;

import exceptions.FloorNotValidException;
import services.ElevatorService;

public class Tp2Main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		ElevatorService elevator = new ElevatorService();

		System.out.println(elevator.getStatus());
		
		elevator.goToFloor(5);
		System.out.println(elevator.getStatus());
		elevator.goToFloor(10);
		System.out.println(elevator.getStatus());
		elevator.goToFloor(-1);
		System.out.println(elevator.getStatus());
		elevator.goToFloor(2);
		System.out.println(elevator.getStatus());
		elevator.goToFloor(-2);
		System.out.println(elevator.getStatus());
		
		//Debe fallar dado a que 11 no es un piso valido
		try {
			elevator.goToFloor(11);
			System.out.println(elevator.getStatus());
		} catch (FloorNotValidException f){
			f.printStackTrace();
		}
		//Debe fallar dado a que -3 no es un piso valido
		try {
			elevator.goToFloor(-3);
			System.out.println(elevator.getStatus());
		} catch (FloorNotValidException f){
			f.printStackTrace();
		}
	}

}
