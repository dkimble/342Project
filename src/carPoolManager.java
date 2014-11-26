import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Implements the operations to be performed on the car pool groups
 * @author Group 2
 * @version 1.0.0
 */
@SuppressWarnings("resource")

public class carPoolManager {

	/**
	 * Used so only one copy of the car pool manager can be created
	 */
	private static carPoolManager instance = null;
	
	/**
	 * An instance of the event manager so different event reports can be handled
	 */
	private EventManager eventManager;
	
	/**
	 * keeps track of all the groups created
	 */
	private LinkedList<carPoolGroup> groupList; 
	
	/**
	 * keeps track of all people, in all groups, in one self contained list
	 */
	private LinkedList<person> personList;  

	/**
	 * Exists to prevent multiple instantiations
	 */
	protected carPoolManager() {
		
	}


	//static instantiation of carPoolManager class
	/**
	 * Create a carPoolManager so that only one can be created
	 * @return The new carPoolManager
	 */
	public static carPoolManager getInstance() {
		if(instance == null) {
			instance = new carPoolManager();
			instance.eventManager = new EventManager(); //associates a new event creator with the class
			instance.groupList = new LinkedList<carPoolGroup>(); 
			instance.personList = new LinkedList<person>();
		}
		return instance;
	}


	/**
	 * Creates a new carpoolGroup and adds it to the appropriate lists
	 * @param initiator First person required to start the group
	 * @param maxCapacity The greatest number of people that can fit in the group
	 * @return The new carPoolGroup
	 */
	public carPoolGroup createNewCarpoolGroup(person initiator, int maxCapacity){
		carPoolGroup newGroup = new carPoolGroup(maxCapacity); //creates a new carpool group
		newGroup.addPerson(initiator); //adds person to the newly created group
		personList.add(initiator); //adds a person to the list of all people
		eventManager.registerObserver(newGroup); //registers the new group to receive event notifications
		groupList.add(newGroup);
		return newGroup;
	}


	
	/**
	 * Prompt user for input to create a person object
	 * @return a person object created from the user input
	 */
	public person promptUserForPersonInfo(){
		String name;
		String phoneNumber;
		int distFromSchool;


		Scanner in = new Scanner(System.in);
		System.out.print("\nPlease enter your name: ");
		name = in.nextLine();
		System.out.print("\nPlease enter your phone number: ");
		phoneNumber = in.nextLine();		  
		distFromSchool = getInt("\nHow many miles do you live from school?: ");
		//in.nextLine(); //removes any newline characters
		person newPerson = new person(name,phoneNumber,distFromSchool); //creates a new person based on user input 

		return newPerson;
	}


	/**
	 * adds a user to a carPoolGroup
	 * @return TRUE if the user is added to the carpoolgroup specified by the GroupID 
	 * FALSE if the Group cannot be found by GroupID, or the group is already full
	 */
	public boolean addUserToGroup(){
		int groupID = getInt("\nWhich Group (GroupID) would you like to join?");
		carPoolGroup cpGroup = getGroupByID(groupID); //Get the carpoolGroup by GroupID
		if(cpGroup != null){
			if(cpGroup.getCurCapacity() >= cpGroup.getMaxCapacity()){
				//Group found, but group is at maxCapacity
				return false;
			}
			person newPerson = promptUserForPersonInfo();
			cpGroup.addPerson(newPerson);
			personList.add(newPerson);
			return true;
		}
		//Group not found, by GroupID
		return false;
	}

	/**
	 * gets a reference to a carPoolGroup by the GroupID 
	 * @param groupID The id of the group to retrieve
	 * @return The carpoolgroup associated with group ID if found.
	 * null if the group is not found
	 */
	public carPoolGroup getGroupByID(int groupID){
		carPoolGroup cpGroup = null;
		for(carPoolGroup G : groupList){ //search through all the carPoolGroups in the list and get the group corresponding to that groupID
			if ( G.getGroupID() == groupID){
				cpGroup = G;
				break;
			}
		}
		return cpGroup; //if reached, cpGroup is still null from first line in method
	}  

	/**
	 * removes a group by reference to that group
	 * @param groupToRemove The group to be removed from the car pool list
	 * @return True if the group is removed, false otherwise
	 */
	public boolean removeGroup(carPoolGroup groupToRemove){

		if(this.groupList.removeFirstOccurrence(groupToRemove)){
			LinkedList<person> pL = groupToRemove.getRiderList(); //get rider list in this group
			for(person p : pL){
				p.setGroupID(0); //reset each person in group to have no group ( "0" )
			}
			return true;
		}
		return false;
	}

	
	/**
	 * removes a group by groupID
	 * @param groupID The ID of the group to be removed
	 * @return True if the group is removed, false otherwise
	 */
	public boolean removeGroup(int groupID){
		carPoolGroup cpGroup = this.getGroupByID(groupID);
		if(cpGroup != null){
			LinkedList<person> pL = cpGroup.getRiderList(); //get rider list in this group
			for(person p : pL){
				p.setGroupID(0); //reset each person in group to have no group ( "0" )
			}
			return true;
		}
		return false;
	}   

	
	/**
	 * Get the list of all car pool groups
	 * @return A linked list of carPoolGroup
	 */
	public LinkedList<carPoolGroup> getGroupList (){
		return this.groupList;
	}
	
	/**
	 * gets the list of all people in the ride share program
	 * @return A linked list of person objects
	 */
	public LinkedList<person> getGlobalPersonList(){
		return this.personList;
	}

	
	/**
	 * gets a reference to a person object, by that person's unique ID
	 * @param personID The Id of the person to look up
	 * @return An object reference to the person
	 */
	public person getPersonById(int personID){
		person personRef = null;
		for(person p : this.personList){
			if(p.getPersonID() == personID){
				return p;
			}
		}
		return personRef; //if reached, a null person is returned
	}

	/**
	 * Update the personal information of a person
	 * @param personToUpdate The person to be updated
	 */
	public void updateRiderInfo(person personToUpdate){
		String name;
		String phoneNumber;
		int distFromSchool;

		//Ask User for new info
		Scanner in = new Scanner(System.in);
		System.out.print("\nPlease enter your name: ");
		name = in.nextLine();
		System.out.print("\nPlease enter your phone number: ");
		phoneNumber = in.nextLine();		  
		distFromSchool = getInt("\nHow many miles do you live from school?: ");

		personToUpdate.setName(name);
		personToUpdate.setPhoneNumber(phoneNumber);
		personToUpdate.setDistFromSchool(distFromSchool);

		return;
	}

	/**
	 * Create a new event and notify all groups of the new event
	 * @param eventType An int defining the event type: 1 for weather, 2 for traffic
	 */
	public void updateGroupsDueToEvent(int eventType){
		int delay = getInt("How long is the delay? : ");
		if(eventType == 1)
			eventManager.newEvents(new WeatherEvent(delay));
		else if(eventType == 2)
			eventManager.newEvents(new TrafficEvent(delay));
	}

	/**
	 * Wrapper class to prompt the user for an integer
	 * @param msg The prompt to display to the user
	 * @return The integer input by the user
	 */
	private int getInt(String msg)
	{
		Scanner in = new Scanner(System.in);
		int result = -1;
		boolean loop = true;
		while (loop) {
			try {
				System.out.printf(msg);
				result = in.nextInt();
				//in.nextLine(); //clears newline
				loop = false;
			} catch (InputMismatchException e) {
				System.out.println("ERROR: Expecting integer value.");
				in.next();
			}
		}

		return result;
	}   

}
