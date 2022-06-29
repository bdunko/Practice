package stacksqueues;

import java.util.LinkedList;
import java.util.Queue;

import testing.Test;

//Implement a data structure that represents an animal shelter of cats and dogs
//When adopting a pet from the shelter, adoptive families can pick to either get
//* the animal that arrived the longest ago
//* the cat that arrived the longest ago
//* the dog that arrived the longest ago
//Support enqueue, dequeueAny, dequeueCat, and dequeueDog
public class AnimalShelter {
	
	public enum AnimalType {
		CAT, DOG;
	}
	
	//Animal class with an age variable which is used to determine if the oldest cat or oldest dog is oldest overall
	public static class Animal {
		private String name;
		private AnimalType type;
		private int age;
		private static int AGE_COUNTER = Integer.MAX_VALUE;
		
		public Animal(String name, AnimalType type) {
			this.name = name;
			this.type = type;
			this.age = Animal.AGE_COUNTER;
			Animal.AGE_COUNTER--;
		}
		
		public String getName() {
			return this.name;
		}
		
		public AnimalType getType() {
			return this.type;
		}
	}
	
	private Queue<Animal> dogs;
	private Queue<Animal> cats;
	
	public AnimalShelter() {
		this.dogs = new LinkedList<Animal>();
		this.cats = new LinkedList<Animal>();
	}
	
	//Returns the oldest animal, regardless of whether it is a cat or dog
	public Animal dequeueAny() {
		Animal oldestDog = dogs.peek();
		Animal oldestCat = cats.peek();
		
		if(oldestDog == null && oldestCat == null)
			return null;
		else if (oldestDog == null)
			return cats.remove();
		else if (oldestCat == null)
			return dogs.remove();
		
		return oldestDog.age > oldestCat.age ? dogs.remove() : cats.remove();
	}
	
	//Returns the oldest cat
	public Animal dequeueCat() {
		if(cats.size() == 0)
			return null;
		
		return cats.remove();
	}
	
	//Returns the oldest dog
	public Animal dequeueDog() {
		if(dogs.size() == 0 )
			return null;
		
		return dogs.remove();
	}
	
	//Adds a new animal to the shelter
	public void enqueue(String name, AnimalType type) {
		Animal newAnimal = new Animal(name, type);
		switch(newAnimal.type) {
			case CAT:
				cats.add(newAnimal);
				break;
			case DOG:
				dogs.add(newAnimal);
			default:
				break;	
		}
	}
	
	public static void main(String[] args) {
		Test.header("AnimalShelter");
		
		AnimalShelter shelter = new AnimalShelter();
		Test.isNull(shelter.dequeueAny());
		Test.isNull(shelter.dequeueCat());
		Test.isNull(shelter.dequeueDog());
		
		shelter.enqueue("CAT ONE", AnimalType.CAT);
		shelter.enqueue("CAT TWO", AnimalType.CAT);
		shelter.enqueue("DOG ONE", AnimalType.DOG);
		shelter.enqueue("CAT THREE", AnimalType.CAT);
		shelter.enqueue("DOG TWO", AnimalType.DOG);
		shelter.enqueue("DOG THREE", AnimalType.DOG);
		shelter.enqueue("CAT FOUR", AnimalType.CAT);
		
		Test.equals(shelter.dequeueAny().getName(), "CAT ONE");
		Test.equals(shelter.dequeueDog().getName(), "DOG ONE");
		Test.equals(shelter.dequeueAny().getName(), "CAT TWO");
		Test.equals(shelter.dequeueAny().getName(), "CAT THREE");
		Test.equals(shelter.dequeueCat().getName(), "CAT FOUR");
		shelter.enqueue("DOG FOUR", AnimalType.DOG);
		shelter.enqueue("CAT FIVE", AnimalType.CAT);
		Test.equals(shelter.dequeueAny().getName(), "DOG TWO");
		Test.equals(shelter.dequeueAny().getName(), "DOG THREE");
		Test.equals(shelter.dequeueAny().getName(), "DOG FOUR");
		Test.equals(shelter.dequeueAny().getName(), "CAT FIVE");
		
		Test.isNull(shelter.dequeueAny());
		Test.isNull(shelter.dequeueCat());
		Test.isNull(shelter.dequeueDog());
		
		Test.results();
	}
}
