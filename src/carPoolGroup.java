import java.util.LinkedList;


/**
 * Controls the functionality of a carpool group
 *
 */
public class carPoolGroup implements Observer {
	/**
	 * used to generate group id's
	 */
	private static int count = 1; 
	
	/**
	 * The identifying id for the group
	 */
	private int groupID;	
	
	/**
	 * The greatest number of riders who can be in the carpool
	 */
	private int maxCapacity;
	
	/**
	 * The current number of riders in the carpool
	 */
	private int curCapacity;
	
	/**
	 * Amount of delay time from received events
	 */
	private int delayTime;

	/**
	 * A linked list of all members of the carpool
	 */
	private LinkedList<person> riderList;
	
	/**
	 * Creates a new carpool group with the given capacity
	 * @param maxCapacity The greatest number of members the carpool can have
	 */
	public carPoolGroup(int maxCapacity){
		this.groupID = carPoolGroup.count;
		carPoolGroup.count++;
		this.maxCapacity = maxCapacity;
		this.curCapacity = 0;
		this.riderList = new LinkedList<person>();
		this.delayTime = 0;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public int getCurCapacity() {
		return curCapacity;
	}
    public void setCurCapacity(int curCapacity) {
		this.curCapacity = curCapacity;
	}

	public int getGroupID() {
		return groupID;
	}

	public LinkedList<person> getRiderList() {
		return riderList;
	}
	
	/**
	 * Adds a person to the carpool if there is space
	 * @param newPerson The person to be added
	 * @return true if person was added successfully, false otherwise
	 */
	public boolean addPerson(person newPerson){
		if(this.curCapacity < this.maxCapacity){
			this.riderList.add(newPerson);
			newPerson.setGroupID(this.groupID);
			this.updateSchedule();	
			this.curCapacity++;
			return true; //person successfully added
		}else{
			return false;
		}
		
	}
	
	public void update(CarPoolEvent e)
	{
		delayTime += e.getTimeDelay();
	}
	
	/**
	 * Rotates the driver of the car so all people get an equal share of driving
	 */
	public void updateSchedule()
	{
		//updates the driving schedule
		delayTime = 0;
		
		// Move the first person on the list to the last position
		person temp = riderList.remove(0);
		riderList.addLast(temp);
	}
	
	/**
	 * Displays the daily schedule for the a group
	 * Includes who is driving and how early that driver should leave to arrive on time
	 */
	public void displaySchedule()
	{
			String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
			
			for(String day : daysOfWeek)
			{
			int maxDist = maxDistance(riderList);
			int driverDist = riderList.getFirst().getDistFromSchool();
			
			// Driver should drive to the farthest person away then turn back towards school
			int travelTime = calculateTravelTime(maxDist-driverDist) + calculateTravelTime(maxDist) + delayTime;
			
			System.out.println(day);
			
			// Print out the driver and his travel time
			System.out.print("Driver : " + riderList.getFirst().getName());
			System.out.println(displayDepartureTime(travelTime));
			
			// Prints out the riders in the carpool
			for(int i = 1; i<riderList.size(); i++)
			{
				int passengerPickUpTime = calculateTravelTime(riderList.get(i).getDistFromSchool());
				
				System.out.print("Rider " + i + ": " + riderList.get(i).getName());
				System.out.println(displayDepartureTime(passengerPickUpTime));
			}
			
			System.out.print("\n\n");
			updateSchedule();
			}
	}
	
	/**
	 * Finds how far the longest distance from any rider to school is
	 * @param p The linked list of passengers in the car pool
	 * @return The farthest any of the people live from school
	 */
	private static int maxDistance(LinkedList<person> p)
	{
		int max = 0;
		
		// Search through list for largest distance
		for(int i = 0; i<p.size(); i++)
		{
			if(p.get(i).getDistFromSchool() > max)
				max = p.get(i).getDistFromSchool();
		}

		return max;
	}
	
	/**
	 * Calculates how long a drive of a certain distance should take
	 * @param distance The distance that needs to be traveled
	 * @return The amount of time in minutes the drive should take
	 */
	private static int calculateTravelTime(int distance)
	{
		// Assume an average driving speed of 20 MPH
		int avgMPH = 20;
		
		double timeInHours = (double)distance / avgMPH;
		int timeInMinutes = (int)(timeInHours * 60);
		
		return timeInMinutes;
	}
	
	private static String displayDepartureTime(int travelTime)
	{
		String time;
		int hours = 8;
		int minutes = 60;
		
		int travelHours = travelTime / 60;
		int travelMinutes = travelTime % 60;
		
		if(travelMinutes != 0)
		{
			minutes -= travelMinutes;
		}
		else
		{
			hours++;
			minutes = 0;
		}
		
		hours -= travelHours;
		if(minutes < 10)
		{
			time = "\tDeparture Time: " + hours + ":0" + minutes + " A.M.";
		}
		else
		{
			time = "\tDeparture Time: " + hours + ":" + minutes + " A.M.";
		}
		
		return time;
	}
}
