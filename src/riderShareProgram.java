			
import java.util.Scanner;

public class riderShareProgram {

	public static void main(String[] args) {
		
		//create one and only carpool manager
		carPoolManager carpoolManager = carPoolManager.getInstance();
		EventManager eventManager = new EventManager();
		
		System.out.print("\n******* Welcome to the University RideShare Program *******");
		System.out.print("\n\nPlease select an operation:");
		System.out.print("\n\t1) Create a new carpool group");
		System.out.print("\n\t2) Create add a rider to a group");
		System.out.print("\n\t3) Remove a group");
		System.out.print("\n\t4) Show all groups");
		System.out.print("\n\t5) Show schedule of a group");
		System.out.print("\n\t6) Update contact info for a rider");
		System.out.print("\n\t7) Exit");
		System.out.printf("\n:");
  
       Scanner in = new Scanner(System.in);

       int selection = 0;
       while( (selection = in.nextInt()) != 7){
    	   
    	   switch (selection){
    		   
    	   		case 1:
    	   				System.out.print("");
    	   				//carpoolManager.createNewCarpoolGroup(newPerson, initCapacity);
    	   			
    	   			
    	   		case 2:
    	   			
    	   		case 3:
    	   			
    	   		case 4:   
    	   			
    	   		case 5:   
    	   			
    	   		case 6:
    	   			
    	   		case 7:
    	   		
    	   			
    	   		System.out.printf("\n:");
    	   }//end switch statement
    	   
    	   
    	   
       }//end while - exit program

		
	in.close();
		
		
	}

}
