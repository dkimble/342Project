
/**
 * Defines a person object to participate in the carpool
 * @author Group 2
 *
 */
public class person {
	private static int count = 1;
	private String name;
	private String phoneNumber;
	private int groupID;
	private int personID;
	private int distFromSchool;


	/**
	 * Create a new person object with the given information
	 * @param name The name of the person
	 * @param phoneNumber Phone number as a string
	 * @param distanceFromSchool The distance in miles they live from the school
	 */
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
