import java.util.LinkedList;


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
	
	   public carPoolGroup createNewCarpoolGroup(person initiator, int maxCapacity){
		   carPoolGroup newGroup = new carPoolGroup(maxCapacity);
		   newGroup.addPerson(initiator);
		   eventManager.registerObserver(newGroup);
		   
		   return newGroup;
	   }
	   
	   public person promptUserForContactInfo(){
		   person newPerson = new person("","",5);
		   
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
