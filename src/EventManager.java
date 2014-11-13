import java.util.ArrayList;


/**
 * Handles newly created events and notifies CarPoolGroups of new events
 * 
 * @author Matt
 * @version 1.0.0
 */
public class EventManager implements Subject 
{
	/**
	 * A collection of all observers watching for events
	 */
	private ArrayList<Observer> observers;
	
	
	/**
	 * A collection of all events currently being handled by the manager
	 */
	private ArrayList<CarPoolEvent> events;
	
	
	public void registerObserver(Observer o)
	{
		observers.add(o);
	}
	
	public void removeObserver(Observer o)
	{
		if(!observers.remove(o));
			System.err.println("Failed to remove observer");
	}
	
	public void notifyObservers(CarPoolEvent e)
	{
		for(Observer o : observers)
		{
			o.update(e);
		}
	}
	
	/**
	 * Adds a new event and notifies observers that it has been created
	 * @param e The new event to be added
	 */
	public void newEvents(CarPoolEvent e)
	{
		events.add(e);
		notifyObservers(e);
	}
	
}
