import guessGame.exercise1.GuessNumberGame;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.HashSet;
import java.util.Set;


public class GuessNumberGameTest {
    private static GuessNumberGame game;

    @BeforeClass
    public static void setUp() {
        game = new GuessNumberGame();
    }

    @Test
    public void verifyIsValidGuessWithUniqueDigits() {
        Assert.assertTrue(game.isValidGuess("1234"));
        Assert.assertTrue(game.isValidGuess("2345"));
        Assert.assertTrue(game.isValidGuess("9853"));
    }

    @Test
    public void verifyIsValidGuessNotStartingWithZero() {
        Assert.assertFalse(game.isValidGuess("0234"));
        Assert.assertFalse(game.isValidGuess("0345"));
        Assert.assertFalse(game.isValidGuess("0853"));
    }

    @Test
    public void verifyIsValidGuessWithNonUniqueDigits() {
        Assert.assertFalse(game.isValidGuess("2213"));
        Assert.assertFalse(game.isValidGuess("9999"));
        Assert.assertFalse(game.isValidGuess("5727"));
    }

    @Test
    public void verifyIsValidGuessWithSizeDifferentFrom4() {
        Assert.assertFalse(game.isValidGuess(""));
        Assert.assertFalse(game.isValidGuess("10000"));
        Assert.assertFalse(game.isValidGuess("1"));
        Assert.assertFalse(game.isValidGuess("999"));
    }

    @Test
    //We check that the number generated is valid
    public void verifyGenerateHiddenNumber() {
        String hiddenNumber = game.generateHiddenNumber();
        char[] digitArray = hiddenNumber.toCharArray();
        Set<Character> digits = new HashSet<>();
        for (int i = 0; i < digitArray.length; i++) {
            if (i == 0) {
                //Checking that we are not generating numbers that begin with '0'
                Assert.assertFalse(digitArray[0] == '0');
            }
            digits.add(digitArray[i]);
        }
        Assert.assertTrue(digits.size() == 4);
    }
}