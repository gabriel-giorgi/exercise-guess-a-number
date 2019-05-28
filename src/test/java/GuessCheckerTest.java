import guessGame.GuessChecker;
import guessGame.ScoreCounter;
import org.junit.Assert;
import org.junit.Test;

public class GuessCheckerTest {

    @Test
    public void verifyCheckGuessWithWinningGuess() {
        String hiddenNumber = "1234";
        ScoreCounter counter = GuessChecker.getScore(hiddenNumber, "1234");
        Assert.assertEquals(4, counter.goodMatches);
        Assert.assertEquals(0, counter.regularMatches);
    }

    @Test
    public void verifyCheckGuessWithPartialGuess() {
        String hiddenNumber = "5624";
        ScoreCounter counter =  GuessChecker.getScore("4657", hiddenNumber);
        Assert.assertEquals(1,counter.goodMatches);
        Assert.assertEquals(2,counter.regularMatches);
    }

    @Test
    public void verifyCheckGuessWithNoMatch() {
        String hiddenNumber = "1267";
        ScoreCounter counter = GuessChecker.getScore("0459", hiddenNumber);
        Assert.assertEquals(0,counter.goodMatches);
        Assert.assertEquals(0,counter.regularMatches);
    }

}
