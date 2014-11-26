			
import java.util.LinkedList;
import java.util.Scanner;

public class riderShareProgram {

	public static void main(String[] args) {
		
		//create one and only one carpool manager
		carPoolManager CPManager = carPoolManager.getInstance();
		EventManager eventManager = new EventManager();
		
		riderShareProgram.printOptions();
  
       Scanner in = new Scanner(System.in);

       int selection = 0;
       while( (selection = in.nextInt()) != 9){
    	   
    	   switch (selection){
    		   
    	   		case 1:
    	   				person newPerson = CPManager.promptUserForPersonInfo();
    	   				System.out.print("\nMax people for this group? : ");
    	   				//String test =in.nextLine(); //clears the newline character from stdin
    	   				int initCapacity = in.nextInt();
    	   				carPoolGroup newGroup = CPManager.createNewCarpoolGroup(newPerson, initCapacity);
    	   				System.out.printf("\nSuccess. Group ID: %d , Your RiderID: %d",newGroup.getGroupID(),newPerson.getPersonID() );
    	   				break;
    	   			  			
    	   		case 2:
    	   			if(CPManager.addUserToGroup()){
    	   				System.out.print("\nSuccess.");
    	   			}else{
    	   				System.out.print("\nFailed: Group full or GroupID does not exist.");
    	   			}
    	   			break;
    	   				
    	   			
    	   		case 3:
    	   			//the user wants to remove a group. Ask user which groupID to remove.
    	   			System.out.print("Which GroupId would you like to remove? : ");
    	   			int groupID = in.nextInt();	
    	   			//removes carPoolGroup, if the ID exists
    	   			if(CPManager.removeGroup(groupID)){
    	   				System.out.printf("\nSuccess, GroupID = %d Removed.",groupID);
    	   			}else{
    	   				System.out.printf("\nFailed: GroupID = %d does not exist.",groupID);
    	   			}
    	   			break;
    	   		
    	   			
    	   		case 4:  //List each group by ID, Displays Current Capacity and MaxCapacity  
    	   			LinkedList<carPoolGroup> cpGList = CPManager.getGroupList();
    	   			for(carPoolGroup cpg : cpGList){
    	   				System.out.printf("\nGroupID: %d ,Current Capacity = %d , Max Capacity = %d", cpg.getGroupID(),cpg.getCurCapacity(),cpg.getMaxCapacity());
    	   			}
    	   			break;
    	   			
    	   		case 5:  
    	   			System.out.print("Which group do you want the schedule for? : ");
    	   			int scheduleGroupNumber = in.nextInt();
    	   			carPoolGroup temp = CPManager.getGroupByID(scheduleGroupNumber);
    	   			if(temp == null)
    	   			{
    	   				System.out.println("Group Not Found");
    	   			}
    	   			else
    	   			{
    	   				temp.displaySchedule();
    	   			}
    	   			break;
    	   		case 6: 
    	   			System.out.printf("\nWhich Group do you want to see info for? : ");
    	   			groupID = in.nextInt();
    	   			carPoolGroup cpg = CPManager.getGroupByID(groupID);
    	   			if(cpg != null){
    	   				System.out.printf("\nGroupID: %d ,Current Capacity = %d , Max Capacity = %d", cpg.getGroupID(),cpg.getCurCapacity(),cpg.getMaxCapacity());
    	   				System.out.printf("\n\t****  Rider Info  ****");
    	   				LinkedList<person> personList = cpg.getRiderList();
    	   				for(person P : personList){
    	   					System.out.printf("\n\t%s , %s , RiderID = %d ",P.getName(),P.getPhoneNumber(),P.getPersonID());
    	   				}
    	   			}else{
    	   				System.out.printf("\nFailed: GroupId not found.");
    	   			}
    	   			
    	   			break;
    	   		
    	   		case 7:
    	   			System.out.printf("\nWhat is your RiderID? :");
    	   			int personID = in.nextInt();
    	   			person personRef = CPManager.getPersonById(personID);
    	   			if(personRef != null){
    	   				CPManager.updateRiderInfo(personRef);
    	   			}else{
    	   				System.out.print("\nFailed: RiderID not found.");
    	   			}
    	   			break;    	   			
    	   			
    	   		case 8:
    	   			System.out.printf("\nWhat is the ID of the rider you'd like to see contact info for :");
    	   			personID = in.nextInt();
    	   			personRef = CPManager.getPersonById(personID);
    	   			if(personRef != null){
    	   				System.out.printf("\n%s, %s",personRef.getName(),personRef.getPhoneNumber() );
    	   			}else{
    	   				System.out.print("\nFailed: RiderID not found.");
    	   			}
    	   			break;
    	   		
    	   }//end switch statement
    	   
    	   //print user choice options
    	   riderShareProgram.printOptions(); 
    	   
       }//end while - exit program

		
	in.close(); //close input stream
		
		
	}//end main

	public static void printOptions(){
		System.out.print("\n\nPlease select an operation:");
		System.out.print("\n\t1) Create a new carpool group");
		System.out.print("\n\t2) Add a rider to a group");
		System.out.print("\n\t3) Remove a group");
		System.out.print("\n\t4) Show all groups");
		System.out.print("\n\t5) Show schedule of a group");
		System.out.print("\n\t6) Show detailed group info");
		System.out.print("\n\t7) Update rider contact infor");
		System.out.print("\n\t8) Get another rider's Contact info");
		System.out.print("\n\t9) Exit");
		System.out.printf("\n:");
	}
}
