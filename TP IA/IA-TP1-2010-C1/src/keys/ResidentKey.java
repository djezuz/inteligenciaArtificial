package keys;

public class ResidentKey {

	//Codigo genetico de X bits (ej 32, 64, 128, etc.) diferenciados en XY
	private String geneticCode;
	
	public ResidentKey(String geneticCode) {
		this.geneticCode = geneticCode;
	}
	
	@Override
	public int hashCode() {
		return geneticCode.hashCode();
	}
}
