package Assignment1;

//VIP Player class
public class VIPPlayer extends Player {
	
	//No arg constructor
	public VIPPlayer() {
	}
	
	//Constructor that creates player with specified name and points
	public VIPPlayer(String name, int points) {
		super(name, points);
	}
	
	//Method to return string player representation for leaderboard
	@Override
	public String toString() {
		return String.format("%-17s %-17o",getName() + "(VIP)", getPoints());
	}
}
