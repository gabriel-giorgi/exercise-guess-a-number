import guessGame.GuessChecker;
import guessGame.ScoreCounter;
import guessGame.exercise2.GuessNumberGameAI;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

public class GuessNumberGameAITest {
    private static GuessNumberGameAI game;

    @BeforeClass
    public static void setUp() {
        game = new GuessNumberGameAI();
    }

    @Test
    public void verifyIsValidNumberWithUniqueDigits() {
        Assert.assertTrue(game.isValidNumber("1234"));
        Assert.assertTrue(game.isValidNumber("5689"));
        Assert.assertTrue(game.isValidNumber("1579"));
        Assert.assertTrue(game.isValidNumber("9753"));
    }

    @Test
    public void verifyIsValidNumberWithNonUniqueDigits() {
        Assert.assertFalse(game.isValidNumber("1233"));
        Assert.assertFalse(game.isValidNumber("9689"));
        Assert.assertFalse(game.isValidNumber("5555"));
        Assert.assertFalse(game.isValidNumber("1751"));
    }

    @Test
    public void verifyFindCandidateGuessWithSuccess(){
        HashMap<String, ScoreCounter> filters = new HashMap<>();
        filters.put("5678", new ScoreCounter(0,2));
        filters.put("7810", new ScoreCounter(0,2));
        String candidateGuess = game.findCandidateGuess(filters);
        Assert.assertNotNull(candidateGuess);
        Assert.assertEquals(0, GuessChecker.getScore(candidateGuess,"5678").goodMatches);
        Assert.assertEquals(2,GuessChecker.getScore(candidateGuess,"5678").regularMatches);
        Assert.assertEquals(0,GuessChecker.getScore(candidateGuess,"7810").goodMatches);
        Assert.assertEquals(2,GuessChecker.getScore(candidateGuess,"7810").regularMatches);
    }

    @Test
    public void verifyFindCandidateGuessWithNoSuccess(){
        HashMap<String, ScoreCounter> filters = new HashMap<>();
        //We entered contradictions in the filters so it will be impossible to find a match
        filters.put("5678", new ScoreCounter(0,4));
        filters.put("7810", new ScoreCounter(2,2));
        String candidateGuess = game.findCandidateGuess(filters);
        Assert.assertNull(candidateGuess);
    }
}
