package domains;

import java.util.Random;

public class Resident {

	public static final int GENES_BITS = 32;
	public static final int MAX_AGE = 70;
	public static final int MIN_REPR_AGE = 18;
	public static final int MAX_REPR_AGE = 50;
	
	public static final String [] SEX = { "M", "F"};
	public static final String [] GENES = { "X", "Y"};

	private static int SEQ_ID = 0;
	
	//id unico de control - puede usarse para que no se repitan los objetos
	private int residentId; 
	//Codigo genetico de X bits (ej 32, 64, 128, etc.) diferenciados en XY
	private String geneticCode;
	
	private boolean reproductible;
	private int age;
	private int aptitud;
	private String sex;
	
	private int score;
	
	public void setScore(int score){
		this.score = score;
	}
	public int getScore(){
		return score;
	}
	
	public Resident(){
		this(getNewId(),true);
	}
	private static synchronized int getNewId(){
		return SEQ_ID++;
	}
	private Resident(int id, boolean isNew){
		this.residentId = id;
		if (isNew){
			fillRandomData();
		}
	}

	private void fillRandomData() {
		Random random = new Random();
		age = random.nextInt(MAX_AGE);
		reproductible = age > MIN_REPR_AGE && age < MAX_REPR_AGE && random.nextInt(2)>0;
		aptitud = random.nextInt(1000);
		sex = SEX[random.nextInt(SEX.length)];
		
		StringBuffer geneBuffer = new StringBuffer(GENES_BITS);
		for (int i = 0; i < GENES_BITS; i++){
			geneBuffer.append(GENES[random.nextInt(GENES.length)]);
		}
		geneticCode = geneBuffer.toString();
		

		
		calculateScore();
		
	}
	
	public int calculateScore(){
		double reprodScore = 1.0;
		double ageScore = 1.0;
		if (age < MIN_REPR_AGE){
			ageScore = 2.0;
		} else if (age > MAX_REPR_AGE){
			ageScore = 0.8;
			reprodScore = 0.6;
		} else if (age > MAX_AGE){
			ageScore = 0.5;
			reprodScore = 0.6;
		} else { //En edades reproducibles
			ageScore = 1.5;
			if (!reproductible){
				reprodScore = 0.6;
			} else {
				reprodScore = 1.2;
			}
		}
		score = (int)Math.round(aptitud * ageScore * reprodScore);
		return score;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Resident cloned = new Resident(this.residentId,false);
		copyData(cloned);
		return cloned;
	}
	private void copyData(Resident cloned) {
		cloned.age = this.age;
		cloned.aptitud = this.aptitud;
		cloned.geneticCode = this.geneticCode;
		cloned.reproductible = this.reproductible;
		cloned.sex = this.sex;
		cloned.score = this.score;
	}
	
	@Override
	public String toString() {
		return "[Resident: id="+residentId+"; reproductible="+reproductible+"; age="+age+"; aptitud="+aptitud+"; score="+score+"; geneticCode=\""+geneticCode+"\"];";
	}
	
	@Override
	public int hashCode() {
		return residentId; //el residentId debe ser unico por lo que sirve como clave hash.
	}
	
	public int getResidentId() {
		return residentId;
	}

	public boolean isReproductible() {
		return reproductible;
	}
	public String getSex() {
		return sex;
	}
	public String getGeneticCode() {
		return geneticCode;
	}
	public int getAge() {
		return age;
	}
	public static Resident createChild(String geneticCode) {
		Resident child = new Resident();
		
		child.age = 0;
		child.reproductible = false;
		child.geneticCode = geneticCode;
		child.calculateScore();
		
		return child;
	}
}
