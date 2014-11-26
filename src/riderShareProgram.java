
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * User interface for the ride share program
 * @author Group 2
 * @version 1.0.0
 */
public class riderShareProgram {

	public static void main(String[] args) {

		//create one and only one carpool manager
		carPoolManager CPManager = carPoolManager.getInstance();

		riderShareProgram.printOptions();

		Scanner in = new Scanner(System.in);

		int selection = 0;
		while( (selection = in.nextInt()) !=  11){

			switch (selection){
			
			// Create a new group
			case 1:
				person newPerson = CPManager.promptUserForPersonInfo();
				int initCapacity = getInt("\nMax people for this group? : ");
				carPoolGroup newGroup = CPManager.createNewCarpoolGroup(newPerson, initCapacity);
				System.out.printf("\nSuccess. Group ID: %d , Your RiderID: %d",newGroup.getGroupID(),newPerson.getPersonID() );
				break;
			
			// Add a person to an existing group
			case 2:
				if(CPManager.addUserToGroup()){
					System.out.print("\nSuccess.");
				}else{
					System.out.print("\nFailed: Group full or GroupID does not exist.");
				}
				break;

			// Remove a group
			case 3:
				//the user wants to remove a group. Ask user which groupID to remove.
				int groupID = getInt("Which GroupId would you like to remove? : ");
				//removes carPoolGroup, if the ID exists
				if(CPManager.removeGroup(groupID)){
					System.out.printf("\nSuccess, GroupID = %d Removed.",groupID);
				}else{
					System.out.printf("\nFailed: GroupID = %d does not exist.",groupID);
				}
				break;

			//List each group by ID, Displays Current Capacity and MaxCapacity 
			case 4:   
				LinkedList<carPoolGroup> cpGList = CPManager.getGroupList();
				for(carPoolGroup cpg : cpGList){
					System.out.printf("\nGroupID: %d ,Current Capacity = %d , Max Capacity = %d", cpg.getGroupID(),cpg.getCurCapacity(),cpg.getMaxCapacity());
				}
				break;

			//Display weekly schedule
			case 5:  
				int scheduleGroupNumber = getInt("Which group do you want the schedule for? : ");
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
				
			// Display group info
			case 6: 
				groupID = getInt("\nWhich Group do you want to see info for? : ");
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

			// Update rider info
			case 7:
				int personID = getInt("\nWhat is your RiderID? :");
				person personRef = CPManager.getPersonById(personID);
				if(personRef != null){
					CPManager.updateRiderInfo(personRef);
				}else{
					System.out.print("\nFailed: RiderID not found.");
				}
				break;    	   			

			// Get person info
			case 8:
				personID = getInt("\nWhat is the ID of the rider you'd like to see contact info for :");
				personRef = CPManager.getPersonById(personID);
				if(personRef != null){
					System.out.printf("\n%s, %s",personRef.getName(),personRef.getPhoneNumber() );
				}else{
					System.out.print("\nFailed: RiderID not found.");
				}
				break;

			// Display list of riders
			case 9:
				LinkedList<person> Plist = CPManager.getGlobalPersonList();
				for(person p : Plist){
					System.out.printf("\n%s , RiderId = %d , GroupId = %d", p.getName(), p.getPersonID(),p.getGroupID());
				}
				break;
			// Create an event
			case 10: 
				int eventType = 0;
				while(eventType != 1 && eventType!= 2)
				{
					eventType = getInt("What type of event? \n\t1. Weather\n\t2. Traffic\n");

					if(eventType == 1 || eventType ==2)
					{
						CPManager.updateGroupsDueToEvent(eventType);
						System.out.println("Schedules updated...");
					}
					else
					{
						System.out.println("Not a valid choice");
					}
				}

			}//end switch statement

			//print user choice options
			riderShareProgram.printOptions(); 

		}//end while - exit program


		in.close(); //close input stream


	}//end main

	
	/**
	 * Wrapper class to get an integer input from the user
	 * @param msg The prompt to display to the user
	 * @return The integer entered by the user
	 */
	private static int getInt(String msg)
	{
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		int result = -1;
		boolean loop = true;
		while (loop) {
			try {
				System.out.printf(msg);
				result = in.nextInt();
				loop = false;
			} catch (InputMismatchException e) {
				System.out.println("ERROR: Expecting integer value.");
				in.next();
			}
		}

		return result;
	}
	
	/**
	 * Display rider share program functions/options to the user
	 */
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
		System.out.print("\n\t9) Show all riders");
		System.out.print("\n\t10) Report event causing delay");
		System.out.print("\n\t11) Exit");
		System.out.printf("\n:");
	}
}
