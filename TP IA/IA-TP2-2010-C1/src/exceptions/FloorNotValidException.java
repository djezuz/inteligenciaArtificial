package exceptions;

public class FloorNotValidException extends Exception {
	public FloorNotValidException(String msg){
		super(msg);
	}
	public FloorNotValidException(String msg, Throwable e) {
		super(msg,e);
	}
}
