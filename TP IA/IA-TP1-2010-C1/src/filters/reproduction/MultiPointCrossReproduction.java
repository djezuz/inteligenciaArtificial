package filters.reproduction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import services.PopulationParameters;
import domains.Resident;
import filters.Filter;

public class MultiPointCrossReproduction extends Filter {

	private List<Resident> reprodMales;
	private List<Resident> reprodFemales;
	
	public MultiPointCrossReproduction() {
		super();
	}
	public MultiPointCrossReproduction(int bestScore, List<Resident> population) {
		super(bestScore, population);
	}

	private void fillReprodFemales() {
		fillReprod(reprodFemales,"F");
	}

	private void fillReprodMales() {
		fillReprod(reprodMales,"M");		
	}

	private void fillReprod(List<Resident> reprodRes, String sex) {
		for (Resident res: population){
			if (res.isReproductible() && sex.equals(res.getSex()))
				reprodRes.add(res);
		}
	}

	@Override
	public List<Resident> applyFilter() {
		reprodMales = new ArrayList<Resident>();
		reprodFemales = new ArrayList<Resident>();
		fillReprodMales();
		fillReprodFemales();
		
		Random random = new Random();
		List<Resident> reproductedChilds = new ArrayList<Resident>();
		int maxCouples = Math.min(reprodMales.size(), reprodFemales.size());
		int maxReproduction = population.size() * PopulationParameters.REPRODUCTION_PRCT / 100;
		
		int couplesSel = 0;
		int reproducted = 0;
		while (couplesSel < maxCouples && reproducted < maxReproduction){
			couplesSel++;
			
			int manIdx = random.nextInt(reprodMales.size());
			Resident Man = reprodMales.get(manIdx);
			int womanIdx = random.nextInt(reprodFemales.size());
			Resident Woman = reprodFemales.get(womanIdx);
			
			//randomicamente dice si la pareja reproduce
			if (true || random.nextBoolean()){
				Resident child = makeChild(Man,Woman);
				reproductedChilds.add(child);
			}
			reprodMales.remove(manIdx);
			reprodFemales.remove(womanIdx);
		}
		
		return reproductedChilds;
	}

	private Resident makeChild(Resident man, Resident woman) {
		String genMan = man.getGeneticCode();
		String genWoman = woman.getGeneticCode();
		
		StringBuffer childGeneticCode = new StringBuffer();
		
		int genBit = 0;
		int step = 0;
		int [] parts = {1,1,2,4};
//		int pos = 0;
		while (genBit < Resident.GENES_BITS){
			int qtyBits = parts[step%parts.length];
			
			if (step%2 == 0){//primer y tercer posicion para la madre, 2da y 4ta para el padre. Indices 0 a 3
				childGeneticCode.append(genWoman.substring(genBit, genBit + qtyBits));
			} else {
				childGeneticCode.append(genMan.substring(genBit, genBit + qtyBits));
			}
			
			
//			pos+=qtyBits;
			genBit+=qtyBits;
			step++;
		}
		
		
		Resident child = Resident.createChild(childGeneticCode.toString());
		
		return child;
	}

}
