import java.util.LinkedList;


public class carPoolGroup implements Observer {
	private static int count = 1; //used to generate group id's
	private int groupID;	
	private int maxCapacity;
	private int curCapacity;
	
	// Amount of time to leave before schedules arrival time
	private int delayTime;
	private LinkedList<person> riderList;
	
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
	
	public void updateSchedule()
	{
		//updates the driving schedule
		delayTime = 0;
		
		person temp = riderList.remove(0);
		riderList.addLast(temp);
	}
	
	public void displaySchedule()
	{
			
			int maxDist = maxDistance(riderList);
			int driverDist = riderList.getFirst().getDistFromSchool();
			
			int travelTime = calculateTravelTime(maxDist) + calculateTravelTime(maxDist-driverDist) + delayTime;
			
			System.out.print("Driver: " + riderList.getFirst().getName());
			System.out.println("  Travel Time: " + travelTime + " Minutes");
			for(int i = 1; i<riderList.size(); i++)
			{
			System.out.println("Rider " + i + ": " + riderList.get(i).getName());
			}
	}
	
	private static int maxDistance(LinkedList<person> p)
	{
		int max = 0;
		
		for(int i = 0; i<p.size(); i++)
		{
			if(p.get(i).getDistFromSchool() > max)
				max = p.get(i).getDistFromSchool();
		}

		return max;
	}
	
	private static int calculateTravelTime(int distance)
	{
		int avgMPH = 20;
		double timeInHours = (double)distance / avgMPH;
		int timeInMinutes = (int)(timeInHours * 60);
		return timeInMinutes;
	}
}
