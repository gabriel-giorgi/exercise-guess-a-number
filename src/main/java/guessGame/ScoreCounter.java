package guessGame;

public class ScoreCounter {
    public int goodMatches;
    public int regularMatches;

    public ScoreCounter(int goodMatches, int regularMatches){
        this.goodMatches = goodMatches;
        this.regularMatches = regularMatches;
    }

    //Format for the score of the guess.
    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder();
        if (this.goodMatches > 0) {
            answer.append(this.goodMatches);
            answer.append("G ");
        }
        if (this.regularMatches > 0) {
            answer.append(this.regularMatches);
            answer.append("R");
        }
        if (this.goodMatches == 0 && this.regularMatches == 0) {
            System.out.print("No match at all!");
        }
        return answer.toString();
    }
}
