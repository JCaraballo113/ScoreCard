package Golf;

/**
 * Created by John Caraballo on 12/9/2015.
 * Contact: jcaraballo019@gmail.com
 */
public class Hole {

    private String mHoleLabel;
    private int mScore = 0;

    public Hole(String holeLabel, int score) {
        mHoleLabel = holeLabel;
        mScore = score;
    }

    public String getHoleLabel() {
        return mHoleLabel;
    }

    public void setHoleLabel(String holeLabel) {
        mHoleLabel = holeLabel;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int score) {
        mScore = score;
    }
}
