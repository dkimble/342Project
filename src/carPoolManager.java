import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

@SuppressWarnings("unused")

public class carPoolManager {
	   
	   private static carPoolManager instance = null;
	   private EventManager eventManager;
	   private LinkedList<carPoolGroup> groupList; //keeps track of all the groups created
	   private LinkedList<person> personList;  //keeps track of all people, in all groups, in one self contained list
	   
	   protected carPoolManager() {
	      // Exists only to defeat instantiation.
	   }
	   
	   
	   //static instantiation of carPoolManager class
	   public static carPoolManager getInstance() {
	      if(instance == null) {
	         instance = new carPoolManager();
	         instance.eventManager = new EventManager(); //associates a new event creator with the class
	         instance.groupList = new LinkedList<carPoolGroup>(); 
	         instance.personList = new LinkedList<person>();
	      }
	      return instance;
	   }
	
	   
	   //Create a new carpoolGroup
	   //Needs one person to initiate group
	   //Also requires initiating person to set the max capacity of people for group.
	   public carPoolGroup createNewCarpoolGroup(person initiator, int maxCapacity){
		   carPoolGroup newGroup = new carPoolGroup(maxCapacity); //creates a new carpool group
		   newGroup.addPerson(initiator); //adds person to the newly created group
		   personList.add(initiator); //adds a person to the list of all people
		   eventManager.registerObserver(newGroup); //doesn't work , NewGroup type does not match Observer type???
		   groupList.add(newGroup);
		   return newGroup;
	   }
	   
	   
	   //simple method to ask user for their contact info. Since we do this frequently.
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
	   
	   
	   //adds a user to a carPoolGroup
	   //returns TRUE if the user is added to the carpoolgroup specified by the GroupID
	   //Returns FALSE if the Group cannot be found by GroupID, or the group is already full
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
	   
	   //gets a reference to a carPoolGroup by the GroupID 
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
	   
	   //removes a group by reference to that group
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
	   
	   //removes a group by groupID
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
	   
	   //gets the carpoolgroup list
	   public LinkedList<carPoolGroup> getGroupList (){
		   return this.groupList;
	   }
	   //gets the list of all people in the ride share program
	   public LinkedList<person> getGlobalPersonList(){
		   return this.personList;
	   }
	   
	   //gets a reference to a person object, by that person's unique ID
	   public person getPersonById(int personID){
		   person personRef = null;
		   for(person p : this.personList){
			   if(p.getPersonID() == personID){
				   return p;
			   }
		   }
		   return personRef; //if reached, a null person is returned
	   }
	   
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
	   
	   public void updateGroupsDueToEvent(int eventType){
		   //loops through all groups and updates them accordingly
	   }
	   public void updateGroupsDueToEvent(int eventType, int groupID){
		   //updates only the specified group to respond to an event 
		   //i.e. some person got sick and no longer needs to be picked up...or something
	   }
	   
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
