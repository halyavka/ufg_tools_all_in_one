package utils;

public class ExtendedPoint {
    private long X;
    private long Y;

    public ExtendedPoint() {
        this(0,0);
    }
    public ExtendedPoint(long X, long Y) {
        this.X = X;
        this.Y = Y;
    }
    public long getX() {
        return X;
    }
    public long getY() {
        return Y;
    }
    public void setX(long X) {
        this.X = X;
    }
    public void setY(long Y) {
        this.Y = Y;
    }

}
