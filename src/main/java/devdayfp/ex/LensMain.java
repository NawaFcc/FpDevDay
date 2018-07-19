package devdayfp.ex;

import java.util.function.BiFunction;
import java.util.function.Function;

import lombok.Value;
import lombok.experimental.Wither;

public class LensMain {

	@Value
	@Wither
	public static class Person {
		private final String name;
		private final Car car;
	}
	@Value
	@Wither
	public static class Car {
		private final String make;
		private final int year;
	}
	
	public static void main(String[] args) {
		Function<Person, Car> personCarRead = person -> person.getCar();
		Function<Car, String>  carMakeRead  = car -> car.getMake();

		BiFunction<Person, Car, Person> personCarChange = (person, newCar) -> person.withCar(newCar);
		BiFunction<Car, String, Car>    carMakeChange   = (car, newMake)   -> car.withMake(newMake);
		
		Function<Person, String>           personCarMarkRead;
		BiFunction<Person, String, Person> personCarMarkChange;
	}
	
	
}
