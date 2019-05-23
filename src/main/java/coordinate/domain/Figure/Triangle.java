package coordinate.domain.Figure;

import coordinate.domain.point.PointGroup;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Triangle extends Figure implements AreaCalculable {
    private static final int POINT_COUNT = 3;

    Triangle(PointGroup points) {
        super(points, POINT_COUNT);
    }

    @Override
    public double area() {
        return applyHeronFormula(getPoints().getSquaredLengths());
    }

    private double applyHeronFormula(List<Double> squaredDistances) {
        return Math.sqrt(4 * squaredDistances.get(0) * squaredDistances.get(1)
                - Math.pow((squaredDistances.get(0) + squaredDistances.get(1) - squaredDistances.get(2)), 2)) / 4;
    }

    @Override
    void validatePoints(PointGroup points) {
        List<Double> distances = points.getSquaredLengths();
        Collections.sort(distances);
        distances = distances.stream().map(x -> Math.sqrt(x)).collect(Collectors.toList());
        if (BigDecimal.valueOf(distances.get(2)).compareTo(BigDecimal.valueOf(distances.get(1) + distances.get(0))) == 0) {
            throw new IllegalArgumentException("일직선 상 3개의 점은 허용하지 않습니다.");
        }
    }

    @Override
    public String toString() {
        return "삼각형";
    }
}
