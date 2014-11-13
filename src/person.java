
public class person {
	private static int count = 1;
	private String name;
	private String phoneNumber;
	private int groupID;
	private int personID;
	private int distFromSchool;
	
	
	public person(String name, String phoneNumber, int distanceFromSchool){
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.distFromSchool = distanceFromSchool;
		this.groupID = 0;
		this.personID = person.count;
		person.count++;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getGroupID() {
		return groupID;
	}
	
	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}
	public int getPersonID() {
		return personID;
	}

	public int getDistFromSchool() {
		return distFromSchool;
	}
	
	public void setDistFromSchool(int distFromSchool) {
		this.distFromSchool = distFromSchool;
	}

}
