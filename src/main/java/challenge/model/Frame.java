package challenge.model;

import java.util.List;

import static java.lang.Integer.parseInt;

public record Frame(List<String> rolls, int score) {

    public int sumFirstTwoRolls() {
        return rolls().stream().limit(2).mapToInt(this::toInt).sum();
    }

    private int toInt(String roll) {
        if ("F".equals(roll)) {
            return 0;
        }
        return parseInt(roll);
    }
}
