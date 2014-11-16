import java.util.LinkedList;
import java.util.Scanner;

@SuppressWarnings("unused")

public class carPoolManager {
	   
	   private static carPoolManager instance = null;
	   private EventManager eventManager;
	   private LinkedList<carPoolGroup> groupList;
	   
	   protected carPoolManager() {
	      // Exists only to defeat instantiation.
	   }
	   
	   public static carPoolManager getInstance() {
	      if(instance == null) {
	         instance = new carPoolManager();
	         instance.eventManager = new EventManager(); //associates a new event creator with the class
	         instance.groupList = new LinkedList<carPoolGroup>(); 
	      }
	      return instance;
	   }
	
	   public void createNewCarpoolGroup(person initiator, int maxCapacity){
		   carPoolGroup newGroup = new carPoolGroup(maxCapacity);
		   newGroup.addPerson(initiator);
		   eventManager.registerObserver(newGroup);
		   
	   }
	   
	   public person promptUserForPersonInfo(){
		   String name;
		   String phoneNumber;
		   int distFromSchool;
		   
		   Scanner in = new Scanner(System.in);
		   System.out.print("\nPlease enter your name: ");
		   name = in.nextLine();
		   System.out.print("\nPlease enter your phone number: ");
		   phoneNumber = in.nextLine();		  
		   System.out.print("\nHow many miles do you live from school?: ");
		   distFromSchool = in.nextInt();
		   
		   person newPerson = new person(name,phoneNumber,distFromSchool);
		   
		   return newPerson;
	   }
	   
	   
	   public void removeGroup(carPoolGroup groupToRemove){
		   this.groupList.removeFirstOccurrence(groupToRemove);
	   }
	   
	   
	   public void updateGroupsDueToEvent(int eventType){
		   //loops through all groups and updates them accordingly
	   }
	   public void updateGroupsDueToEvent(int eventType, int groupID){
		   //updates only the specified group to respond to an event 
		   //i.e. some person got sick and no longer needs to be picked up...or something
	   }
}
