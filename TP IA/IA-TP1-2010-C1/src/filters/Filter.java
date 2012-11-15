package filters;

import java.util.List;

import domains.Resident;

public abstract class Filter {
	protected int bestScore;
	protected List<Resident> population;
	
	public Filter() {
	}
	
	public Filter(int bestScore, List<Resident> population) {
		init(bestScore,population);
	}
	public void init(int bestScore, List<Resident> population){
		this.bestScore = bestScore;
		this.population = population;		
	}

	public abstract List<Resident> applyFilter();
}
