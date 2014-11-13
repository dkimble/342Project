
/**
 * Abstract class to unite a variety of different event types
 * 
 * @author Matt
 * @version 1.0.0
 */
public abstract class CarPoolEvent {
	/**
	 * The length of time by which the event is expected to delay travel
	 */
	private int timeDelay;

	public int getTimeDelay() {
		return timeDelay;
	}

	public void setTimeDelay(int timeDelay) {
		this.timeDelay = timeDelay;
	}
}
