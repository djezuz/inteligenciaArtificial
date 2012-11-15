package filters.selection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import services.PopulationParameters;
import domains.Resident;
import filters.Filter;

public class TournamentSelection extends Filter {

	public TournamentSelection() {
		super();
	}
	public TournamentSelection(int bestScore, List<Resident> population) {
		super(bestScore, population);
	}

	@Override
	public List<Resident> applyFilter() {
		List<Resident> selected = new ArrayList<Resident>();
		Random random = new Random();
		
		int actualPopulationSize = population.size();
		while (selected.size() < actualPopulationSize * PopulationParameters.SELECTION_PRCT / 100){
			int firstIdx = random.nextInt(population.size());
			Resident first = population.get(firstIdx);
			int secondIdx = random.nextInt(population.size());
			Resident second = population.get(secondIdx);
			if (first.getResidentId() != second.getResidentId()){
				if (second.getScore()>first.getScore()){
					selected.add(second);
					population.remove(secondIdx);
				} else {
					selected.add(first);
					population.remove(firstIdx);
				}
			}
		}
		return selected;
	}

}
