package devdayfp.ex;

import java.time.LocalDate;
import java.util.Objects;
import java.util.function.Function;

import lombok.ToString;
import lombok.Value;
import lombok.experimental.Delegate;

public class ValidPersonMain {
	
	@Value
	public static class UncheckedPerson {
		private final String firstName;
		private final String lastName;
		private final LocalDate birthDate;
	}
	
	@ToString
	public static class Person  {
		@Delegate
		private final UncheckedPerson person;
		
		private Person(UncheckedPerson person) {
			this.person = person;
		}
		
		public static Valid<Person> of(UncheckedPerson person) {
			Function<UncheckedPerson, LocalDate> personBirthDate = UncheckedPerson::getBirthDate;
			Function<LocalDate, Integer>         yearOfDate      = LocalDate::getYear;
			Function<UncheckedPerson, Integer>   personBirthYear = personBirthDate.andThen(yearOfDate);
			
			return Valid.value(person)
					.validate(UncheckedPerson::getFirstName, Objects::nonNull,    "No first name")
					.validate(UncheckedPerson::getLastName,  Objects::nonNull,    "No last name")
					.validate(UncheckedPerson::getBirthDate, Objects::nonNull,    "No birth date")
					.validate(personBirthYear,               year -> year < 2000, "Too young.")
					.map(Person::new);
		}
	}
	

	public static void main(String[] args) {
		UncheckedPerson person1 = new UncheckedPerson("Jim", "Rennie", LocalDate.parse("1999-07-19"));
		System.out.println(Person.of(person1));
		
		UncheckedPerson person2 = new UncheckedPerson("Jeramy", "Toplak", null);
		System.out.println(Person.of(person2));
		
		UncheckedPerson person3 = new UncheckedPerson("Ben", "Leech", LocalDate.parse("2000-07-19"));
		System.out.println(Person.of(person3));
	}
	
}
