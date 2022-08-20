package uncategorized;

import testing.Test;

//Given an array of Persons with birth and death years listed (between 1900-2000 inclusive)
//Determine which year has the most people alive
public class LivingPeople {

	private static class Person {
		public int birthYear;
		public int deathYear;
		
		public Person(int birthYear, int deathYear) {
			this.birthYear = birthYear;
			this.deathYear = deathYear;
		}
	}
	
	private static int yearToIndex(int year) {
		return year - 1900;
	}
	
	private static int getYearWithMostPeople(Person[] living) {
		//determine change in population for each year
		int[] deltaPeople = new int[102];
		
		//for each person, increment deltaPeople in birthYear, decrement deltaPeople in deathYear
		for(Person person : living) {
			deltaPeople[yearToIndex(person.birthYear)]++;
			deltaPeople[yearToIndex(person.deathYear)+1]--;
		}
		
		//determine which year has max population
		int largestYear = 1990;
		int largestPopulation = 0;
		int currentYear = 1900;
		int currentPopulation = 0;
		
		for(int i = 0; i < 101; i++) {
			currentPopulation += deltaPeople[i];
			if(currentPopulation > largestPopulation) {
				largestYear = currentYear;
				largestPopulation = currentPopulation;
			}
			currentYear++;
		}
		
		return largestYear;
	}
	
	
	public static void main(String[] args) {
		Test.header("LivingPeople");
		
		Person[] onePerson = {
				new Person(1975, 1975)
		};
		Test.equals(getYearWithMostPeople(onePerson), 1975);
		
		Person[] fewPeople = {
				new Person(1975, 1980),
				new Person(1980, 1983),
				new Person(1990, 2000)
		};
		Test.equals(getYearWithMostPeople(fewPeople), 1980);
		
		Person[] manyPeople = {
				new Person(1975, 1980),
				new Person(1970, 1973),
				new Person(1990, 2000),
				new Person(1972, 2000),
				new Person(1900, 1932),
				new Person(1966, 1980),
				new Person(1972, 1972),
				new Person(1920, 1937),
				new Person(1956, 1992),
				new Person(1998, 2000)
		};
		Test.equals(getYearWithMostPeople(manyPeople), 1972);
		
		Test.results();
	}
	
}
