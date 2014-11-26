
/**
 * Interface for any subject in an Observer pattern
 * 
 * @author Group 2
 * @version 1.0.0
 *
 */
public interface Subject 
{
	/**
	 * Adds a new observer to the subject
	 * @param o The new Observer to be added
	 */
	public void registerObserver(Observer o);


	/**
	 * Removes an Observer from the subjects observer collection
	 * @param o The Observer to be removed
	 */
	public void removeObserver(Observer o);


	/**
	 * Notify the observers that an event has been created
	 * @param e The event to notify the observers of
	 */
	public void notifyObservers(CarPoolEvent e);

}
