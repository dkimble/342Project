			
import java.util.Scanner;

public class riderShareProgram {

	public static void main(String[] args) {
		
		//create one and only carpool manager
		carPoolManager CPManager = carPoolManager.getInstance();
		EventManager eventManager = new EventManager();
		
		riderShareProgram.printOptions();
  
       Scanner in = new Scanner(System.in);

       int selection = 0;
       while( (selection = in.nextInt()) != 8){
    	   
    	   switch (selection){
    		   
    	   		case 1:
    	   				person newPerson = CPManager.promptUserForPersonInfo();
    	   				System.out.print("\nMax people for this group?: ");
    	   				//String test =in.nextLine(); //clears the newline character from stdin
    	   				int initCapacity = in.nextInt();
    	   				carPoolGroup newGroup = CPManager.createNewCarpoolGroup(newPerson, initCapacity);
    	   				System.out.printf("\nSuccess. Group ID: %d , Your ID: %d",newGroup.getGroupID(),newPerson.getPersonID() );
    	   				break;
    	   			  			
    	   		case 2:
    	   			if(CPManager.addUserToGroup()){
    	   				System.out.print("\nSuccess.");
    	   			}else{
    	   				System.out.print("\nFailed: Group full or GroupID does not exist.");
    	   			}
    	   			   
    	   				
    	   			
    	   		case 3:
    	   			
    	   		case 4:   
    	   			
    	   		case 5:  
    	   			
    	   		case 6:
    	   			
    	   		case 7:
    	   		
    	   			
    	   		
    	   }//end switch statement
    	   
    	   
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
		System.out.print("\n\t7) Update contact info for a rider");
		System.out.print("\n\t8) Exit");
		System.out.printf("\n:");
	}
}
