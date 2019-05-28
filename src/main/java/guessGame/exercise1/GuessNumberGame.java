package guessGame.exercise1;
import guessGame.GuessChecker;
import guessGame.ScoreCounter;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GuessNumberGame {

    private boolean isGameWon = false;
    private String hiddenNumber;

    public void play() {
        ScoreCounter scoreGuess;
        String guess;
        System.out.println("Welcome to the Guess game.");
        while (!isGameWon) {
            guess = askForGuess();
            if (!isValidGuess(guess)) {
                continue;
            }
            scoreGuess = GuessChecker.getScore(guess, hiddenNumber);
            System.out.println(scoreGuess);
            if (scoreGuess.goodMatches == 4) {
                isGameWon = true;
            }
        }
        System.out.println("You win!");
    }

    public GuessNumberGame() {
        //Generating the hidden number to guess
        hiddenNumber = generateHiddenNumber();
    }

    //Function for generating the hidden number to guess
    public String generateHiddenNumber() {
        List<Integer> tmpNumber =  IntStream.range(0, 10).boxed().collect(Collectors.toList());
        Collections.shuffle(tmpNumber);
        tmpNumber = tmpNumber.subList(0, 4);
        //We make sure that the first digit is not 0
        while(tmpNumber.get(0).equals(0)){
            Collections.shuffle(tmpNumber);
        }
        StringBuilder str = new StringBuilder();
        for(Integer i : tmpNumber){
            str.append(i);
        }
        return str.toString();
    }

    private String askForGuess() {
        System.out.println("Please, Enter your guess(it should have unique digits between 1023-9876): ");
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    public boolean isValidGuess(String guess) {
        //Regular expression for validating a 4 digit number with unique digits starting from 1023
        return guess.matches("^(?!.*(.).*\\1)\\d{4}") && guess.charAt(0) != '0';
    }

}
