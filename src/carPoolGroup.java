import java.util.LinkedList;


public class carPoolGroup implements Observer {
	private static int count = 1; //used to generate group id's
	private int groupID;	
	private int maxCapacity;
	private int curCapacity;
	private LinkedList<person> riderList;
	
	public carPoolGroup(int maxCapacity){
		this.groupID = carPoolGroup.count;
		carPoolGroup.count++;
		this.maxCapacity = maxCapacity;
		this.curCapacity = 0;
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
	
	public void addPerson(person newPerson){
		this.riderList.add(newPerson);
		this.updateSchedule();	
	}
	
	public void update(CarPoolEvent e)
	{
		
	}
	
	public void updateSchedule(){
		//updates the driving schedule
	}
}
