import java.util.LinkedList;


public class carPoolGroup {
	private int groupID;
	private int maxCapacity;
	private int curCapacity;
	private LinkedList<person> riderList;
	
	public carPoolGroup(int maxCapacity){
		this.groupID = 1;
		this.maxCapacity = maxCapacity;
		this.curCapacity = 1;
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

	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}

	public LinkedList<person> getRiderList() {
		return riderList;
	}
	
	public void addPerson(person newPerson){
		this.riderList.add(newPerson);
		this.updateSchedule();
		
	}
	
	public void updateSchedule(){
		
	}
}
