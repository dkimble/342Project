
/**
 * An event caused by weather conditions expected to delay travel
 * 
 * @author Group 2
 * @version 1.0.0
 */
public class WeatherEvent extends CarPoolEvent {

	/**
	 * Creates a new weather event with the given length of delay 
	 * @param delay The length of the delay caused by the weather
	 */
	public WeatherEvent(int delay)
	{
		setTimeDelay(delay);
	}
}
