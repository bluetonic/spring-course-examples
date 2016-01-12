package net.gfu.seminar.spring.helloworld;

import java.util.Scanner;

/**
 * A guest.
 * 
 * @author tf
 *
 */
public class Guest implements InterfaceGuest {

	private String firstName;
	private String lastName;

	
	public Guest() {
		this("", "");
	}
	
	/**
	 * Creates a new {@link Guest} with the given first- and lastname.
	 * 
	 * @param firstName
	 * @param lastName
	 */
	public Guest(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getName() {
		return this.firstName + " " + this.lastName;
	}

	public void setName(String name) {
		Scanner scanner = new Scanner(name);
		this.firstName = scanner.next();
		this.lastName = scanner.next();
		scanner.close();
	}

	@Override
	public String toString() {
		return "Guest [firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
