package filters.selection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import services.PopulationParameters;

import domains.Resident;
import filters.Filter;

public class RouletteSelection extends Filter {

	private final int bucketsCount = 100;
	
	public RouletteSelection() {
		super();
	}
	public RouletteSelection(int bestScore, List<Resident> population) {
		super(bestScore, population);
	}

	@Override
	public List<Resident> applyFilter() {
		HashSet<Resident> [] buckets = new HashSet[bucketsCount];
		for (int i=0; i < bucketsCount; i++){
			buckets[i] = new HashSet<Resident>();
		}
		
		HashSet<Resident> selected = new HashSet<Resident>();
		Random random = new Random();
		
		//Reparto la poblacion en los buckets segun su Score de aptitud.
		for (Resident res : population){
			int qtyBuckets = (int)((double)res.getScore())*bucketsCount/bestScore;
			
			int failTries = 0;
			int insertedBuckets = 0;
			while (insertedBuckets < qtyBuckets && failTries < 100){
				int nextBucket = random.nextInt(bucketsCount);
				
				if (buckets[nextBucket].contains(res)){
					failTries++;
				} else {
					buckets[nextBucket].add(res);
					insertedBuckets++;
				}
			}
		}
		
		int actualPopulationSize = population.size();
		
		while (selected.size() < actualPopulationSize * PopulationParameters.SELECTION_PRCT / 100){
			selected.addAll(buckets[random.nextInt(bucketsCount)]);
		}
		
		return new ArrayList<Resident>(selected);
	}

}
