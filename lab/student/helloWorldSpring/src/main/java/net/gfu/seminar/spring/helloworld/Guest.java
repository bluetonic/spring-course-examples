package net.gfu.seminar.spring.helloworld;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Scanner;

/**
 * A guest.
 * 
 * @author tf
 *
 */
@Entity @Table(name="guests")
@XmlRootElement
public class Guest implements InterfaceGuest {

	private String firstName;
	private String lastName;

	@Id @GeneratedValue(strategy= GenerationType.AUTO)
	private long id;

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

	public Guest(long id, String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
