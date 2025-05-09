package practice.com;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random rd = new Random(); // Random Number Generator
		
		int totalScore =0; // Score tracker for total round won
		boolean continuePlaying = true; // flag to control game loop
		System.out.println("Welcome To Number Guessing Game!");

		while(continuePlaying) {
			int numberToGuess =rd.nextInt(100)+1;//To Generate Number between 1 to 100
			int maxAttempts = 5; // Maximum attempts per round
			boolean guessedCorrectly = false;
			
			System.out.println("The number has been selected between 1 and 100");
			System.out.println("Can you guess it within " + maxAttempts + "attempts? let's find out!");
			
			//Inner loop for limited attempts (5 attempts)
			for(int attempt =1; attempt <=maxAttempts; attempt++) {
				System.out.println("Attempt " + attempt + " - Your guess:");
				int userGuess = sc.nextInt();//User's Guess
				
				//checking if the guess is correct
				if(userGuess == numberToGuess) {
					System.out.println("Well Done! You guessed it in " + attempt + "attempt(s).");
					totalScore++;
					guessedCorrectly = true;
					break;
				
				}
				else if(userGuess<numberToGuess) {
					System.out.println("Oops! too low");
				}
				else {
					System.out.println("Too high! try something lower");
				}
				
				//show remaining attempts
				if(attempt<maxAttempts) {
					System.out.println("Remaining attempts " + (maxAttempts - attempt));
				}
				
			}
			
			//if user did not guess correctly in given attempts
			if(!guessedCorrectly) {
				System.out.println("You are out of Attempts. The Correct Number was: " + numberToGuess);
			}
			
			//Ask if user wants to play another round
			System.out.println("Want to Play Another Round? (yes/no):");
			String playMore = sc.next().trim().toLowerCase();
			continuePlaying = playMore.equals("yes");
		}
		
	//final score display
	System.out.println("\nThanks for Playing! You Won " + totalScore + "round(s)");
	sc.close(); //close scanner to avoid resoruce leak
	}
}
       
