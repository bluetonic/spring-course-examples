package net.gfu.seminar.spring.helloworld;


import org.springframework.beans.factory.annotation.Autowired;

/**
 * Says hello to a {@link Guest}.
 *
 * @author tf
 *
 */
public class Greeting {

	private Guest guest;

	public Greeting() {
	}

	@Autowired
	public Greeting( Guest guest) {
		this.setGuest(guest);
	}

	/**
	 * Returns the welcome message.
	 *
	 * @return
	 */
	public String welcome() {
		return String.format("Welcome %1$s to Spring!", this.getGuest());
	}

	/**
	 * Accessor method returning the internal state.
	 * @return the guest
	 */
	public Guest getGuest() {
		return guest;
	}

	/**
	 * Mutator method changing the internal state.
	 * @param guest the guest
	 */
	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public void init() {
		System.out.println("init() called on "+this.toString());
	}

	public void destroy() {
		System.out.println("destroy() called on "+this.toString());
	}

	@Override
	public String toString() {
		return "Greeting{" +
				"guest=" + guest +
				'}';
	}

}
