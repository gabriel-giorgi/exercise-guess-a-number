package guessGame;

public class GuessChecker {

    private GuessChecker() { }
    //Method for calculating the score between 2 numbers.
    public static ScoreCounter getScore(String guess, String candidateGuess) {
        ScoreCounter resultGuessCounter = new ScoreCounter(0, 0);
        char[] hiddenDigits = candidateGuess.toCharArray();
        char[] guessDigits = guess.toCharArray();
        for (int i = 0; i < guessDigits.length; i++) {
            if (guessDigits[i] == hiddenDigits[i]) {
                resultGuessCounter.goodMatches++;
            } else if (candidateGuess.contains(Character.toString(guessDigits[i]))) {
                resultGuessCounter.regularMatches++;
            }
        }
        return resultGuessCounter;
    }

}