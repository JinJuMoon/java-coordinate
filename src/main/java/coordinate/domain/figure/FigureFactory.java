package coordinate.domain.figure;

import coordinate.domain.Point;
import coordinate.exception.InvalidFigureException;

import java.util.Arrays;
import java.util.List;

public enum FigureFactory {
    LINE_CREATOR(2, new LineCreator()),
    TRIANGLE_CREATOR(3, new TriangleCreator()),
    RECTANGLE_CREATOR(4, new RectangleCreator());

    private int sizeOfPoints;
    private FigureCreator figureCreator;


    FigureFactory(int sizeOfPoints, FigureCreator figureCreator) {
        this.sizeOfPoints = sizeOfPoints;
        this.figureCreator = figureCreator;
    }

    public static FigureCreator generateFigureCreator(int sizeOfPoints) {
        return Arrays.stream(values())
                .filter(f -> f.sizeOfPoints == sizeOfPoints)
                .map(figureFactory -> figureFactory.figureCreator)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public static Figure getFigure(List<Point> points) throws InvalidFigureException {
        FigureCreator figureCreator = FigureFactory.generateFigureCreator(points.size());
        if (figureCreator == null) {
            throw new InvalidFigureException("유효하지 않은 도형입니다.");
        }
        return figureCreator.create(points);
    }
}

class LineCreator implements FigureCreator {
    @Override
    public Figure create(List<Point> points) {
        return new Line(points);
    }
}

class TriangleCreator implements FigureCreator {
    @Override
    public Figure create(List<Point> points) {
        return new Triangle(points);
    }
}

class RectangleCreator implements FigureCreator {
    @Override
    public Figure create(List<Point> points) {
        return new Rectangle(points);
    }
}