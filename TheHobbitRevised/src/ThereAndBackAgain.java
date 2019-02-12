import java.util.ArrayList;

public class ThereAndBackAgain 
{

	public static void main(String[] args) 
	{
		
		Hobbit frodo = new Hobbit("Frodo");
		Hobbit sam = new Hobbit("Sam");
		Dwarf gimli = new Dwarf("Gimli");
		
		// Create a traveling party called party1 by creating an array of Travelers 
		// and filling it with frodo, sam, and gimli
		Traveler[] party1 = {frodo, sam, gimli};
		// Then, use a loop to make all travelers go a distance of 50 miles  
		for(Traveler  thisTraveler : party1) {
			thisTraveler.travel(50);
			System.out.println(thisTraveler.getName() + " has traveled " + thisTraveler.getDistanceTraveled() + " miles.");
		}
		// Then, for each Traveler in the travelingParty, print their name and how far they've
		//    traveled in miles.  (In the next piece, you'll do this in methods, but 
		//    for a first pass, just do it in main and print to the console.)
		// Expected output:  Frodo has traveled 50 miles.
		//                   Sam has traveled 50 miles.
		//                   Gimli has traveled 50 miles.
		
		System.out.println("\n\n\nPART 2: \n");

		String[] dwarfNames = {"Fili", "Kili", "Dori", "Ori", "Nori", "Balin", "Dwalin", 
		"Oin", "Gloin", "Bifur", "Bofur", "Bombur", "Thorin"};

		// Make a new ArrayList to hold a 2nd party of Travelers called party2:
		ArrayList<Traveler> party2 = new ArrayList<Traveler>();
		
		// Call the createParty method and pass party2 and the dwarfNames array.
		for(String thisDwarf : dwarfNames) {
			party2.add(thisDwarf);
			
		}

		// Finally, call the allTravel method passing it party2 and 100 (representing
		// the 100 miles that party2 has traveled together.
		
		
		
		
		System.out.println();

	}
	public static String[] createParty(ArrayList<Traveler> traveler, String[] dwarfNames) {
		return null;
		
	}
}
