package domains;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import services.PopulationService;
import filters.Filter;

public class Population implements Iterable<Resident> {

	private ArrayList<Resident> population;
	private int bestScore = 0;
	
	private static Population uniqueInstance;
	
	public static Population getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new Population();
		}
		return uniqueInstance; 
	}
	
	private Population(){
		population = new ArrayList<Resident>();
		
		PopulationService.initPopulation(this);
	}

	//@Override
	public Iterator<Resident> iterator() {
		return population.iterator();
	}

	public void addResident() {
		Resident newResident = new Resident();
		int addedScore = newResident.getScore();
		
		population.add(newResident);
		
		if (addedScore > bestScore)
			bestScore = addedScore;
		
	}
	
	public int getBestScore() {
		return bestScore;
	}
	
	@SuppressWarnings("unchecked")
	public List<Resident> getPopulation() {
		return (List<Resident>) population.clone();
	}

	public void applyFilters(Class [] filters) throws InstantiationException, IllegalAccessException {
		
		HashSet<Resident> newPopulation = new HashSet<Resident>();
		
		for (int i=0; i < filters.length ; i++){
			Filter filter = (Filter) filters[i].newInstance();
			
			filter.init(bestScore, population);
			
			newPopulation.addAll(filter.applyFilter());
		}
		
		population.clear();
		population.addAll(newPopulation);
		
		recalculateScores();
		
	}

	private void recalculateScores() {
		bestScore = 0;
		int score;
		for (Resident res: population){
			score = res.calculateScore();
			
			if (score > bestScore){
				bestScore = score;
			}
		}
	}

}
