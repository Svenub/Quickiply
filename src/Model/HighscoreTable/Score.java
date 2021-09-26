package Model.HighscoreTable;

public class Score {

    private String name;
    private int placing;
    private double score;


    public Score(String name, int placing, double score) {
        this.name = name;
        this.placing = placing;
        this.score = score;
    }

    public Score(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlacing() {
        return placing;
    }

    public void setPlacing(int placing) {
        this.placing = placing;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return placing + "." + name + " - " + score;
    }
}
