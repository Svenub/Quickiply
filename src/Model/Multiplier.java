package Model;


import java.util.ArrayList;
import java.util.List;

public class Multiplier {

    private MathQ currentQ;

    public Multiplier() {
        currentQ = null;
    }

    private List<MathQ> mathQList = new ArrayList<>();

    public MathQ generateQuestion() {
        MathQ mathQ;

        do {
            mathQ = new MathQ("*");
        } while (mathQ.getAnswer() % 10 == 0); // We don't want something like 8 * 10 (TOO EASY!!!)

        currentQ = mathQ;
        return mathQ;
    }

    private MathQ generateAndAddQuestion() {
        MathQ mathQ = generateQuestion();
        mathQList.add(mathQ);
        return mathQ;
    }

}
