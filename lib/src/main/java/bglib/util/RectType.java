package bglib.util;

public class RectType {
    private Vector2d pos, size;

    public RectType(double widthHeight) {
        this(0, 0, widthHeight, widthHeight);
    }
    public RectType(int xPos, int yPos, int sizeX, int sizeY) {
        this(new Vector2i(xPos, yPos), new Vector2i(sizeX, sizeY));
    }
    public RectType(double xPos, double yPos, double sizeX, double sizeY) {
        this(new Vector2d(xPos, yPos), new Vector2d(sizeX, sizeY));
    }
    public RectType(Vector2i pos, Vector2i size) {
        this(pos.asVector2d(), size.asVector2d());
    }
    public RectType(Vector2d pos, Vector2d size)  {
        if (size.x < 0) {
            pos = pos.setX(pos.x+size.x);
            size = size.setX(-size.x);
        }
        if (size.y < 0) {
            pos = pos.setY(pos.y+size.y);
            size = size.setY(-size.y);
        }

        this.pos = pos;
        this.size = size;
    }

    public Vector2d getPos() {
        return pos;
    }

    public Vector2d getSize() {
        return size;
    }

    public void setPos(Vector2d pos) {
        this.pos = new Vector2d(pos);
    }

    public void setSize(Vector2d size) {
        this.size = new Vector2d(size);
    }

    public Vector2d getCenter() {
        return pos.add(size.div(2));
    }

    public boolean within(Vector2d point) {
        return point.x > pos.x && point.x < pos.x+size.x && point.y > pos.y && point.y < pos.y+size.y;
    }

    public String toString() {
        return "Pos: "+pos+", Size: "+size;
    }
}
