package Assignment1;

import java.util.Date;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class MainProject {
		public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
			
		//Creating player array to keep track of all players
		Player [] array = new Player[100];
			
		int userInput = 0;
		do {
			// Prompt user for option
			System.out.println("Please choose an option: ");
			System.out.println("1. New Player");
			System.out.println("2. Quit");
		
		//Checking that input is an integer
		do {	
			try {
				userInput = input.nextInt();
			}
			catch (InputMismatchException e) {
				input.next();
			}
		}while (userInput != (int)userInput);
		
		//Checking for valid integer input
		if (userInput != 1 && userInput != 2) {
			System.out.println("Invalid input");
		}
		
		//Creating new player
		if (userInput == 1) {
			//Prompt user for name
			@SuppressWarnings("resource")
			Scanner input1 = new Scanner(System.in);
			System.out.println("Please enter a name: ");
			String userName = input1.nextLine();
			Player player;
			
			//If player is first they become a VIP player
			if (Player.getNumberOfPlayers() == 0) {
				player = new VIPPlayer(userName, 1);
				System.out.println("Congratulations " + userName + "! You're the first player and have received VIP membership, you've just earned yourself an extra point!");
			}
			else {
				player = new Player(userName, 0);
				System.out.println("Hello " + userName);
			}
			
			//Adding player to player array
			array[Player.getNumberOfPlayers()-1] = player;
			
			
			
			int gameChoice = 0;
			do {
				//Prompt user for game choice
				@SuppressWarnings("resource")
				Scanner input2 = new Scanner(System.in);
				System.out.println("Please choose a game, or -1 to quit:");
				System.out.println("1: Coin Flip");
				System.out.println("2: Number Guess");
				
				//Checking that input is an integer
				do {	
					try {
						gameChoice = input2.nextInt();
					}
					catch (InputMismatchException e) {
						input2.next();
					}
				}while (gameChoice != (int)gameChoice);
				
				
				//Checking for valid integer input
				if (gameChoice != 1 && gameChoice != 2 && gameChoice != -1) {
					System.out.println("Invalid input");
				}
				
				//If user enters 1 activate coinFlip method
				if (gameChoice == 1) {
					coinFlip(player);
				}
				
				//If user enters 2 activate numberGuess method
				if (gameChoice == 2) {
					numberGuess(player);
				}
				
			}
			//Exit to main menu if user enters -1
			while(gameChoice != -1);
			
			}
	}
	//Displaying leaderboard if user enters 2
	while (userInput != 2);
	
	//Keeping track of leaderboards in external file
	CreateFile(array);
	
	//Displaying leaderboard 
	displayLeaderboard(array);
	
	}
	
	
	
		
	// Method to implement the Coin Flip game
	public static void coinFlip(Player player) {
		//Display game welcome
		System.out.println("Welcome to the Coin Flip game - best of 3!");
		
		//Initialise wins and turns variables
		int wins = 0;
		int turns = 0;
		
		//While user hasn't guessed correctly twice or exceeded guesses
		do {
			//Prompt user for input
			@SuppressWarnings("resource")
			Scanner input3 = new Scanner(System.in);
			System.out.println("Heads or tails?");
			System.out.println("1: Heads");
			System.out.println("2: Tails");
			
			//Initialising variable
			int coinGuess = 0;
			
			//Checking that input is an integer
			do {	
				try {
					coinGuess = input3.nextInt();
				}
				catch (InputMismatchException e) {
					input3.next();
				}
			}while (coinGuess != (int)coinGuess);
			
			// Checking for invalid input
			if (coinGuess != 1 && coinGuess != 2) {
				System.out.println("Invalid input");
			}
			else {
				//Generate random coin side
				String[] coinSide = {"Heads", "Tails"};
				int index = (int) (Math.random() * coinSide.length);
				String word = coinSide[index];
				
				//If guess is correct
				if (coinGuess == (index + 1)) {
					System.out.println("... " + word);
					System.out.println("You guessed correctly!");
					wins ++;
					turns ++;
				}
				//If guess is incorrect
				else {
					System.out.println("... " + word);
					System.out.println("You guessed incorrectly.");
					turns ++;
				}
			}
		
		}
		while (turns < 3 && wins < 2);
		
		//If user guesses best of 3 print congratulations message and add point to player
		if (wins >= 2) {
			player.addPoint();
			System.out.println("Congratulations you won and gained a point!");
		}
		//If user loses print loser message
		else {
			System.out.println("Sorry you lost :(");
		}
	}
	
	
	
	
	
	// Method to implement the Number Guess game
	public static void numberGuess(Player player) {
		
		//Display game welcome and instructions
		System.out.println("Welcome to the Number Guess game - you have 6 guesses!");
		System.out.println("Guess a number from 0-100");
		
		//Initialise number of turns
		int turns = 0;
		
		//Generate random number between 0 - 100
		int number = (int)(Math.random()*(101));
		
		//Initialise variable
		int guess = 98989898;
		
		//While the guess is not equal to the random number and user hasn't exceeded guesses prompt for input
		while (guess != number && turns < 6) {
			
			//Prompt user for number
			@SuppressWarnings("resource")
			Scanner input4 = new Scanner(System.in);
			System.out.print("Enter a number: ");
			
			//Checking that input is an integer
			do {	
				try {
					guess = input4.nextInt();
				}
				catch (InputMismatchException e) {
					input4.next();
				}
			}while (guess != (int)guess);
			
			//Check that the variable isn't invalid
			if (guess == 98989898) {
				System.out.println("Invalid input");
			}
			else {
				//If the guess is greater than the random number
				if (guess > number) {
					turns ++;
					System.out.println("Too high"); 
					
				}
				//If the guess is less than the random number
				else {
					turns ++;
					System.out.println("Too low"); 
				}
				}
		}
		//If the user guesses the number print congratulations message and add point to player
		if (guess == number) {
			player.addPoint();
			System.out.println("Congratulations you won and gained a point!");
		}
		//If the user loses print loser message
		else {
			System.out.println("Sorry you lost :(");
		}
	}
	
	
	
	
	
	//Method to create and append to file with leaderboard information
	public static void CreateFile(Player [] array) {
		
		// Create a File instance
		File file = new File("leaderboard.txt");

		// Declare a PrintWriter reference variable
		PrintWriter output = null;
		try {
			// Create a PrintWriter object 
			output = new PrintWriter(new FileWriter(file, true)); 

			//Sort players according to points		
			for(int i = 0; i < Player.getNumberOfPlayers(); i++) {
				for (int j = i + 1; j < Player.getNumberOfPlayers(); j++) {
					Player temp;
					if (array[i].getPoints() < array[j].getPoints()) {
						temp = array[i];
						array[i] = array[j];
						array[j] = temp;
					}
				}
			}
			
			// Write output to the file
			output.println(new Date());
			output.println("LEADERBOARD");
						
			for (int i = 0; i < Player.getNumberOfPlayers(); i++) {
				output.println(array[i].toString());
			}		
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			// Close the PrintWriter instance
			if (output != null)
				output.close();
		}
	}
	
	
	
	
	
	
	//Method to display the leaderboard 
	public static void displayLeaderboard(Player [] array) {
		
		//Sorting players according to their score
		for(int i = 0; i < Player.getNumberOfPlayers(); i++) {
			for (int j = i + 1; j < Player.getNumberOfPlayers(); j++) {
				Player temp;
				if (array[i].getPoints() < array[j].getPoints()) {
					temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
			
	//Printing leaderboard
	System.out.println("LEADERBOARD");
	System.out.println("Player    :    Points");
			
	//Printing players in order
	for (int i = 0; i < Player.getNumberOfPlayers(); i++) {
		System.out.println(array[i].toString());
	}	
}
		
		
	
	
}