package services;

import domains.Population;

public class PopulationService {

	public static void initPopulation(Population population){
		int vecPop;
		
		for (vecPop = 0; vecPop < PopulationParameters.POPULATION_SIZE; vecPop++){
			population.addResident();
		}
		
	}


}
