package controllers;

import java.util.Iterator;

import domains.Population;
import domains.Resident;
import filters.reproduction.MultiPointCrossReproduction;
import filters.selection.RouletteSelection;
import filters.selection.TournamentSelection;

public class TpMain {

	/**
	 * @param args
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		Population population = Population.getInstance();
		
		//Poblacion inicial
		int populSize = population.getPopulation().size();
		System.out.println("Poblacion inicial: "+populSize+" habitantes");
		Iterator<Resident> it = population.iterator();
		int f=0,m=0,repr=0,kids=0,adult=0,old=0;
		while (it.hasNext()){
			Resident ind = it.next();
			if ("F".equals(ind.getSex())) f++; else m++;
			
			if (ind.getAge()<Resident.MIN_REPR_AGE) kids++;
			else if (ind.getAge()>Resident.MAX_REPR_AGE) old++;
			else adult++;
			
			if (ind.isReproductible()) repr++;
			
			System.out.println(ind);
		}
		printResumen(f,m,repr,kids,adult,old,populSize,population.getBestScore());

		
		Class [] filters = {RouletteSelection.class,MultiPointCrossReproduction.class};
		population.applyFilters(filters);
		populSize = population.getPopulation().size();
		System.out.println("Primera fase. ");
		System.out.println("Aplicacion de filtros: Seleccion x rueda de ruleta + Reproduccion por Cruza multipunto");
		System.out.println("Poblacion: "+populSize+" habitantes");
		Iterator<Resident> it2 = population.iterator();
		f=0;m=0;repr=0;kids=0;adult=0;old=0;
		while (it2.hasNext()){
			Resident ind = it2.next();
			
			if ("F".equals(ind.getSex())) f++; else m++;
			
			if (ind.getAge()<Resident.MIN_REPR_AGE) kids++;
			else if (ind.getAge()>Resident.MAX_REPR_AGE) old++;
			else adult++;
			
			if (ind.isReproductible()) repr++;
			
			System.out.println(ind);
		}
		printResumen(f,m,repr,kids,adult,old,populSize,population.getBestScore());
		
		System.out.println("Segunda fase. ");
		System.out.println("Aplicacion de filtros: Seleccion x torneo + Reproduccion por Cruza multipunto");
		Class [] filters2 = {TournamentSelection.class,MultiPointCrossReproduction.class};
		population.applyFilters(filters);
		populSize = population.getPopulation().size();
		System.out.println("Poblacion: "+populSize+" habitantes");
		
		it = population.iterator();
		f=0;m=0;repr=0;kids=0;adult=0;old=0;
		while (it.hasNext()){
			Resident ind = it.next();
			
			if ("F".equals(ind.getSex())) f++; else m++;
			
			if (ind.getAge()<Resident.MIN_REPR_AGE) kids++;
			else if (ind.getAge()>Resident.MAX_REPR_AGE) old++;
			else adult++;
			
			if (ind.isReproductible()) repr++;
			
			System.out.println(ind);
		}
		printResumen(f,m,repr,kids,adult,old,populSize,population.getBestScore());
		
	}

	private static void printResumen(int f, int m, int repr, int kids,
			int adult, int old, int total, int bestScore) {
		System.out.println("Resumen de la poblacion");
		System.out.println("------- -- -- ---------");
		System.out.println("Sexo. Femenino: "+f+". Masculino: "+m);
		System.out.println("Edad. Niños: "+kids+". Adultos: "+adult+". Ancianos"+old);
		System.out.println("Individuos con capacidad de reproduccion: "+repr);
		System.out.println("Total individuos: "+ total+". Mejor score de aptitud: "+bestScore);
		
	}

}
