
/**
 * Interface to be implemented by any observers in the observer pattern
 * 
 * @author Group 2
 * @version 1.0.0
 */
public interface Observer {

	/**
	 * Updates the Observer based on the event notification received
	 * @param e The event to be handled
	 */
	public void update(CarPoolEvent e);
}
