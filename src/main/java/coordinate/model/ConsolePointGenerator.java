package coordinate.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsolePointGenerator implements PointGenerator {
    private static final String POINT_REGEX = "\\(([0-9]+)(,)([0-9]+)\\)";

    List<String> consoleInputs;

    public ConsolePointGenerator(List<String> consoleInputs) {
        this.consoleInputs = consoleInputs;
    }

    @Override
    public List<Point> generate() {
        List<Point> points = new ArrayList<>();
        for (String input : consoleInputs) {
            Matcher matcher = Pattern.compile(POINT_REGEX).matcher(input);
            matcher.matches();
            points.add(new Point(Integer.parseInt(matcher.group(1)),
                    Integer.parseInt(matcher.group(3))));
        }
        return points;
    }
}
