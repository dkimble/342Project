
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

	/**
	 * Get the length of the delay caused by the event
	 * @return The length of the delay in minutes
	 */
	public int getTimeDelay() {
		return timeDelay;
	}

	/** 
	 * Set the length of the delay caused by the event
	 * @param timeDelay The length of the delay in minutes
	 */
	public void setTimeDelay(int timeDelay) {
		this.timeDelay = timeDelay;
	}
}
