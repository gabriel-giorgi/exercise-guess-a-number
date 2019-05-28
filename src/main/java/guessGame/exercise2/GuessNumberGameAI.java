package guessGame.exercise2;


import guessGame.GuessChecker;
import guessGame.ScoreCounter;

import java.util.*;

public class GuessNumberGameAI {
    boolean isGameWon;
    private List<String> candidateNumbers;

    public void play() {
        final String WIN_MESSAGE   = "Computer - I've Won";
        //We make a initial guessNumber
        String initialGuess = candidateNumbers.get(new Random().nextInt(candidateNumbers.size()));
        ScoreCounter initialScoreGuess = guessNumber(initialGuess);
        //Initializes a HashMap with previous guesses and his scores. Every new guess should have the same score regarding each old previous guess
        //Works like a filter for new guesses.
        HashMap<String, ScoreCounter> filters = new HashMap<>();
        filters.put(initialGuess, initialScoreGuess);
        if (initialScoreGuess.goodMatches == 4) {
            System.out.println(WIN_MESSAGE);
            isGameWon = true;
        }
        while (!isGameWon) {
            String candidateGuess;
            //Now we are going to find a candidate for the next game using the previous guesses as "filters"
            candidateGuess = findCandidateGuess(filters);
            if (candidateGuess != null) {
                //Now we do the guess
                ScoreCounter guessScore = guessNumber(candidateGuess);
                if (guessScore.goodMatches == 4) {
                    isGameWon = true;
                }
                filters.put(candidateGuess,guessScore);
            } else{
                System.out.println("it doesn't exist any number that matches with your answers. You might entered a wrong number.\nPlease play again :)");
                break;
            }
        }
        if (isGameWon) System.out.println(WIN_MESSAGE);
    }

    public String findCandidateGuess(Map<String, ScoreCounter> filters) {
        for (String candidateGuess : candidateNumbers) {
            boolean existCandidateGuess = true;
            //We search through the hashMap searching for a candidateGuess that
            //matches his scoreGuess with the ones from previous guesses. The first we find is going to be our next number to guess.
            for (Map.Entry<String, ScoreCounter> entry : filters.entrySet()) {
                String guess = entry.getKey();
                ScoreCounter guessScore = entry.getValue();
                if (!GuessChecker.getScore(candidateGuess, guess).equals(guessScore)) {
                    existCandidateGuess = false;
                    break;
                }
            }
            //We return the candidateGuess that will be the next guess.
            if (existCandidateGuess) {
                return candidateGuess;
            }
        }
        return null;
    }

    public GuessNumberGameAI() {
        candidateNumbers = generateCandidateNumbers();
    }

    private ScoreCounter guessNumber(String guess) {
        System.out.println("Computer - My guess is : " + guess);
        ScoreCounter guessScore = new ScoreCounter(0, 0);
        System.out.println("Insert number of good digits: ");
        guessScore.goodMatches = Integer.valueOf(getUserInput());
        if (guessScore.goodMatches == 4) {
            return guessScore;
        }
        System.out.println("Insert number of regular digits: ");
        guessScore.regularMatches = Integer.valueOf(getUserInput());
        return guessScore;
    }

    private List<String> generateCandidateNumbers() {
        List<String> numbers = new ArrayList<>();
        for (int i = 1023; i <= 9876; i++) {
            String candidateNumber = String.valueOf(i);
            if (isValidNumber(candidateNumber)) {
                numbers.add(candidateNumber);
            }
        }
        return numbers;
    }

    //Used for checking if a number has unique digits.
    public boolean isValidNumber(String number) {
        Set<Character> digitCounter = new HashSet<>();
        char[] digitsArray = number.toCharArray();
        for(Character digit : digitsArray){
            digitCounter.add(digit);
        }
        return (digitCounter.size() == number.length()) ;

    }

    //Handling the userInput.
    private String getUserInput(){
        Scanner scanner = new Scanner(System.in);
        String result = scanner.next();
        while (!result.matches("[0-4]")){
            System.out.println("Please provide a valid number(between 0-4)");
            result = scanner.next();
        }
        return result;
    }
}