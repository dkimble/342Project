
/**
 * An event caused by weather conditions expected to delay travel
 * 
 * @author Matt
 * @version 1.0.0
 */
public class WeatherEvent extends CarPoolEvent {
	
	public WeatherEvent(int delay)
	{
		setTimeDelay(delay);
	}
}
