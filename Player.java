package Assignment1;

//Player class
public class Player {
	private String name;
	private int points;
	private static int numberOfPlayers;
	
	
	//No arg constructor with default variables
	public Player() {
		this.name = "Player";
		this.points = 0;
		Player.numberOfPlayers += 1;
	}
	
	//Constructor that creates Player with specified name and points
	public Player(String name, int points) {
		this.name = name;
		this.points = points;
		Player.numberOfPlayers += 1;
	}
	
	//Name getter
	public String getName() {
		return name;
	}
	
	//Points getter
	public int getPoints() {
		return points;
	}
	
	//Number of players getter
	public static int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	
	//Method to add a point to Player
	public void addPoint() {
		this.points += 1;
	}
	
	//Method to return string representation of player for leaderboard
	public String toString() {
		return String.format("%-17s %-17o",name, points);
	}
}
