import java.util.Scanner;

public class TextLocation {
	
	public static void main(String[] args){
		boolean exit = false;
		Scanner in = new Scanner(System.in);
		int choice;
	while(!exit){
		
		System.out.println("Where would you like to go? : \n(1) the park \n(2) the gym \n(3) the school \n(4) Candyland \n(5) exit");
		choice = Integer.parseInt(in.nextLine());
		
		if (choice == 1){
			while(!exit){
				System.out.println("You go to the park \n\nWhat would you like to do?: \n(1) Go on the swings \n(2) Go on the seesaw");
				choice = Integer.parseInt(in.nextLine());
				
				if(choice == 1){
					System.out.println("You fall off the swing, triggering a land-mine which explodes and kills you");
					exit = true;
				}
				
				else if(choice == 2){
					System.out.println("You have a fun time on the seesaw");
					exit = true;
				}
				
				else{
					System.out.println("That is not a valid option");
				}
			}
		}
		
		else if (choice == 2){
			System.out.println("You got super buff");
		    exit = true;
		}
		
		else if (choice == 3){
			System.out.println("You learn stuff");
			exit = true;
		}
		
		else if (choice == 4){
			System.out.println("You DIED");
			exit = true;
		}
		
		else if (choice == 5){
			exit = true;
		}
		
		else {
			System.out.println("That is not a valid option");
			
		}
		
	}

}
}