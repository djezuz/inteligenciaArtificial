package exceptions;

public class DoorOpenedException extends Exception{

	public DoorOpenedException(String msg){
		super(msg);
	}
	public DoorOpenedException(String msg, Throwable e) {
		super(msg,e);
	}
}
