package location.domain;

public class Rectangle extends Figure {
    private static final String SAME_LINE_MSG = "세점 이상이 한 선상에 있습니다.";
    private static final String NOT_RECTANGLE_MSG = "직사각형이 아닙니다.";

    public Rectangle(final Points points) {
        super(points);
        checkValid(points);
    }

    private void checkValid(final Points points) {
        checkBruteForceSameLine(points);
        checkRectangle(points);
    }

    private void checkRectangle(final Points points) {
        if (isSameDiagonal(points)
                && isSameCenterX(points)
                && isSameCenterY(points)) {
            return;
        }
        throw new IllegalArgumentException(NOT_RECTANGLE_MSG);
    }

    private boolean isSameCenterX(final Points points) {
        return ((points.get(1).getX() + points.get(2).getX()) / 2)
                == ((points.get(0).getX() + points.get(3).getX()) / 2);
    }

    private boolean isSameCenterY(final Points points) {
        return ((points.get(1).getY() + points.get(2).getY()) / 2)
                == ((points.get(0).getY() + points.get(3).getY()) / 2);
    }

    private boolean isSameDiagonal(final Points points) {
        return calculateLine(points.get(1), points.get(2))
                == calculateLine(points.get(0), points.get(3));
    }

    private void checkBruteForceSameLine(Points points) {
        checkSameLine(points);
    }

    private void checkSameLine(Points points) {
        if (isSameLine(points)) {
            throw new IllegalArgumentException(SAME_LINE_MSG);
        }
    }

    private boolean isSameLine(Points points) {
        return calculateTilt(points.get(0), points.get(1)) == calculateTilt(points.get(1), points.get(2))
                && calculateTilt(points.get(1), points.get(2)) == calculateTilt(points.get(2), points.get(3))
                && calculateTilt(points.get(0), points.get(1)) == calculateTilt(points.get(2), points.get(3));
    }

    @Override
    public double calculate() {
        return calculateLine(points.get(0), points.get(1))
                * calculateLine(points.get(0), points.get(2));
    }

    @Override
    public Points getPoints() {
        return this.points;
    }
}
