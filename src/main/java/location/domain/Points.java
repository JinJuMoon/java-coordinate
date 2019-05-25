package location.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Points {
    private final static String POINT_NULL_OR_BLANK_MSG = "포인트가 비어있습니다.";
    private final static String DUPLICATE_MSG = "위치가 같은 점(point)이 존재합니다.";
    private static List<Point> points;

    public Points(final List<Point> points) {
        checkValid(points);
        compare(points);
        Points.points = points;
    }

    private void checkValid(final List<Point> points) {
        isNullOrBlank(points);
        isSamePoint(points);
    }

    private void isNullOrBlank(final List<Point> points) {
        if (points == null || points.isEmpty()) {
            throw new IllegalArgumentException(POINT_NULL_OR_BLANK_MSG);
        }
    }

    private void isSamePoint(final List<Point> points) {
        Set<Point> notDuplicatePoints = new HashSet<>(points);
        if (notDuplicatePoints.size() != points.size()) {
            throw new IllegalArgumentException(DUPLICATE_MSG);
        }
    }

    private void compare(final List<Point> points) {
        points.sort((p1, p2) -> {
            if (p1.getX() == p2.getX()) {
                return p1.getY() - p2.getY();
            }
            return p1.getX() - p2.getX();
        });
    }

    public Point get(final int index) {
        return points.get(index);
    }

    public int size() {
        return points.size();
    }

    public List<Point> getPoints() {
        return points;
    }
}
