import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;

public class PiecingItTogether {
    public static JigsawInfo getCompleteInformation(JigsawInfo input) {
        // There are many possibilities for this, but I only implemented what makes tests pass.
        OptionalInt pieces = input.getPieces();
        Optional<String> format = input.getFormat();
        OptionalInt rows = input.getRows();
        OptionalInt columns = input.getColumns();
        OptionalInt border = input.getBorder();
        OptionalInt inside = input.getInside();
        OptionalDouble aspectRatio = input.getAspectRatio();

        while (pieces.isEmpty() || border.isEmpty() || inside.isEmpty() || rows.isEmpty() || columns.isEmpty() || aspectRatio.isEmpty() || format.isEmpty()) {
            int count = 0;

            if (pieces.isEmpty()) {
                if (format.isPresent() && format.get().equals("square") && rows.isPresent()) {
                    pieces = OptionalInt.of(rows.getAsInt() * rows.getAsInt());
                    count++;
                } else if (format.isPresent() && format.get().equals("square") && columns.isPresent()) {
                    pieces = OptionalInt.of(columns.getAsInt() * columns.getAsInt());
                    count++;
                } else if (rows.isPresent() && columns.isPresent()) {
                    pieces = OptionalInt.of(rows.getAsInt() * columns.getAsInt());
                    count++;
                } else if (border.isPresent() && inside.isPresent()) {
                    pieces = OptionalInt.of(border.getAsInt() + inside.getAsInt());
                    count++;
                } else if (inside.isPresent() && aspectRatio.isPresent()) {
                    pieces = OptionalInt.of(getAreaGivenInsideAndRatio(inside.getAsInt(), aspectRatio.getAsDouble()));
                    count++;
                } else if (rows.isPresent() && aspectRatio.isPresent()) {
                    pieces = OptionalInt.of(getAreaGivenRowsAndRatio(rows.getAsInt(), aspectRatio.getAsDouble()));
                    count++;
                }
            }
            if (border.isEmpty()) {
                if (pieces.isPresent() && inside.isPresent()) {
                    border = OptionalInt.of(pieces.getAsInt() - inside.getAsInt());
                    count++;
                } else if (pieces.isPresent() && aspectRatio.isPresent()) {
                    border = OptionalInt.of(getBorder(pieces.getAsInt(), aspectRatio.getAsDouble()));
                    count++;
                }
            }
            if (inside.isEmpty()) {
                if (pieces.isPresent() && border.isPresent()) {
                    inside = OptionalInt.of(pieces.getAsInt() - border.getAsInt());
                    count++;
                }
            }
            if (rows.isEmpty()) {
                if (pieces.isPresent() && columns.isPresent()) {
                    rows = OptionalInt.of(pieces.getAsInt() / columns.getAsInt());
                    count++;
                } else if (pieces.isPresent() && aspectRatio.isPresent()) {
                    rows = OptionalInt.of(getRowAndColumn(pieces.getAsInt(), 1.0 / aspectRatio.getAsDouble()));
                    count++;
                }
            }
            if (columns.isEmpty()) {
                if (pieces.isPresent() && rows.isPresent()) {
                    columns = OptionalInt.of(pieces.getAsInt() / rows.getAsInt());
                    count++;
                }
            }
            if (aspectRatio.isEmpty()) {
                if (columns.isPresent() && rows.isPresent()) {
                    aspectRatio = OptionalDouble.of((double) columns.getAsInt() / rows.getAsInt());
                    count++;
                } else if (pieces.isPresent() && border.isPresent() && format.isPresent()) {
                    aspectRatio = OptionalDouble.of(getAspectRatio(pieces.getAsInt(), border.getAsInt(), format.get()));
                    count++;
                }
            }
            if (format.isEmpty()) {
                if (aspectRatio.isPresent()) {
                    if (aspectRatio.getAsDouble() > 1) {
                        format = Optional.of("landscape");
                    }
                    if (aspectRatio.getAsDouble() == 1) {
                        format = Optional.of("square");
                    }
                    if (aspectRatio.getAsDouble() < 1) {
                        format = Optional.of("portrait");
                    }
                    count++;
                }
            }

            if (count == 0)
                throw new IllegalArgumentException("Insufficient data");
        }

        boolean borderAndInside = border.getAsInt() + inside.getAsInt() == pieces.getAsInt();
        boolean rowAndColumn = rows.getAsInt() * columns.getAsInt() == pieces.getAsInt();
        boolean ratio = (double) columns.getAsInt() / rows.getAsInt() == aspectRatio.getAsDouble();
        boolean fmt = aspectRatio.getAsDouble() > 1 ?
                format.get().equals("landscape") : aspectRatio.getAsDouble() < 1 ?
                format.get().equals("portrait") :format.get().equals("square");
        if (borderAndInside && rowAndColumn && ratio && fmt) {
            return new JigsawInfo.Builder()
                    .pieces(pieces.getAsInt())
                    .border(border.getAsInt())
                    .inside(inside.getAsInt())
                    .rows(rows.getAsInt())
                    .columns(columns.getAsInt())
                    .aspectRatio(aspectRatio.getAsDouble())
                    .format(format.get())
                    .build();
        }
        else {
            throw new IllegalArgumentException("Contradictory data");
        }
    }

    private static int getRowAndColumn(int pieces, double ratio) {
        return (int)Math.sqrt(pieces * ratio);
    }

    private static int getBorder(int pieces, double ratio) {
        return (2 * getRowAndColumn(pieces, ratio)) + (2 * getRowAndColumn(pieces, 1.0 / ratio)) - 4;
    }

    private static int getAreaGivenInsideAndRatio(int inside, double ratio) {
        return (int)Math.pow(Math.sqrt(ratio) + (1 / Math.sqrt(ratio)) + Math.sqrt(Math.pow(Math.sqrt(ratio) + (1 / Math.sqrt(ratio)), 2) + inside - 4), 2);
    }

    private static int getAreaGivenRowsAndRatio(int rows, double ratio) {
        return (int)(rows * rows * ratio);
    }

    private static double s(int border) {
        return (double)(border + 4) / 2;
    }

    private static double getAspectRatio(int pieces, int border, String format) {
        double numerator = (s(border) - Math.sqrt(s(border) * s(border) - 4 * pieces));
        double denominator = (s(border) + Math.sqrt(s(border) * s(border) - 4 * pieces));
        return format.equals("portrait") ? numerator / denominator : denominator / numerator;
    }
}
