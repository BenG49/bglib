package bglib.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.stuypulse.stuylib.math.Angle;

public class Vector2d {
    public static final Vector2d ORIGIN = new Vector2d(0);

    public final double x, y;

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2d(double xy) {
        this(xy, xy);
    }

    public Vector2d(String asString) {
        String[] coords = asString.replaceAll("\\)", "").replaceAll("\\(", "").replaceAll(" ", "").split(",");
        if (coords.length != 2) {
            System.out.println("Incorrect string given");
            x = 0;
            y = 0;
        } else {
            x = Double.parseDouble(coords[0]);
            y = Double.parseDouble(coords[1]);
        }
    }

    public Vector2d(Vector2d copy) {
        this(copy.x, copy.y);
    }

    public Vector2d(Angle angle, double distance) {
        this.x = Math.cos(angle.toRadians())*distance;
        this.y = Math.sin(angle.toRadians())*distance;
    }


    public Vector2d setX(double x) {
        return new Vector2d(x, this.y);
    }

    public Vector2d setY(double y) {
        return new Vector2d(this.x, y);
    }

    public Vector2d addX(double i) {
        return new Vector2d(this.x+i, this.y);
    }

    public Vector2d addY(double i) {
        return new Vector2d(this.x, this.y+i);
    }

    public Vector2d add(Vector2d a) {
        return new Vector2d(this.x+a.x, this.y+a.y);
    }

    public Vector2d add(Vector2i a) {
        return new Vector2d(this.x+a.x, this.y+a.y);
    }

    public Vector2d add(Angle angle, double distance) {
        return this.add(new Vector2d(
            Math.cos(angle.toRadians())*distance,
            Math.sin(angle.toRadians())*distance
        ));
    }

    public Vector2d sub(Vector2d a) {
        return new Vector2d(this.x-a.x, this.y-a.y);
    }

    public Vector2d sub(Vector2i a) {
        return new Vector2d(this.x-a.x, this.y-a.y);
    }

    public Vector2d add(double a) {
        return new Vector2d(this.x+a, this.y+a);
    }

    public Vector2d sub(double a) {
        return new Vector2d(this.x-a, this.y-a);
    }
    
    public Vector2d mul(double a) {
        return new Vector2d(this.x*a, this.y*a);
    }

    public Vector2d mul(Vector2d a) {
        return new Vector2d(this.x*a.x, this.y*a.y);
    }

    public Vector2d div(double a) {
        return new Vector2d(this.x/a, this.y/a);
    }

    public Vector2d div(Vector2d a) {
        return new Vector2d(this.x/a.x, this.y/a.y);
    }

    public boolean within(Vector2d a, Vector2d b) {
        return this.x >= Math.min(a.x, b.x) && this.x <= Math.max(a.x, b.x)
            && this.y >= Math.min(a.y, b.y) && this.y <= Math.max(a.y, b.y);
    }

    public static HashSet<Vector2d> overallWithin(Iterator<Vector2d> data, Vector2d a, Vector2d b) {
        HashSet<Vector2d> temp = new HashSet<Vector2d>();
        while (data.hasNext())
            temp.add(data.next());

        return overallWithin(temp, a, b);
    }
    public static HashSet<Vector2d> overallWithin(HashSet<Vector2d> data, Vector2d a, Vector2d b) {
        HashSet<Vector2d> output = new HashSet<Vector2d>();

        for (Vector2d pos : data)
            if (pos.within(a, b))
                output.add(pos);
        
        return output;
    }

    public Vector2d floorToInterval(double interval) {
        return new Vector2d(this.x - this.x%interval, this.y - this.y % interval);
    }

    public static Vector2d min(Vector2d a, Vector2d b) {
        return (a.x <= b.x && a.y <= b.y) ? a : b;
    }

    public static Vector2d max(Vector2d a, Vector2d b) {
        return (a.x >= b.x && a.y >= b.y) ? a : b;
    }

    public static Vector2d min(List<Vector2d> data) {
        if (data.size() == 0)
            return null;
        
        Vector2d min = data.get(0);
        for (Vector2d i : data)
            if (Vector2d.min(min, i).equals(i))
                min = i;
        
        return min;
    }

    public static Vector2d max(List<Vector2d> data) {
        if (data.size() == 0)
            return null;
        
        Vector2d max = data.get(0);
        for (Vector2d i : data)
            if (Vector2d.max(max, i).equals(i))
                max = i;
        
        return max;
    }

    public Vector2d abs() {
        return new Vector2d(Math.abs(this.x), Math.abs(this.y));
    }

    public static Vector2d overallMin(HashSet<Vector2d> data) {
        Vector2d min = new Vector2d(Integer.MAX_VALUE);

        for (Vector2d i : data) {
            if (i.x < min.x)
                min = min.setX(i.x);
            if (i.y < min.y)
                min = min.setY(i.y);
        }

        return min;
    }

    public static Vector2d overallMax(HashSet<Vector2d> data) {
        Vector2d max = new Vector2d(Integer.MIN_VALUE);

        for (Vector2d i : data) {
            if (i.x > max.x)
                max = max.setX(i.x);
            if (i.y > max.y)
                max = max.setY(i.y);
        }

        return max;
    }

    public static Vector2d avg(List<Vector2d> points) {
        int totalX = 0, totalY = 0;

        for (Vector2d pos : points) {
            totalX += pos.x;
            totalY += pos.y;
        }

        return new Vector2d(
            totalX / points.size(),
            totalY / points.size()
        );
    }

    public static Vector2d avg(Vector2d a, Vector2d b) {
        return avg(new ArrayList<Vector2d>(Arrays.asList(a, b)));
    }

    public static Vector2d avg(Vector2i a, Vector2i b) {
        return avg(new ArrayList<Vector2d>(Arrays.asList(a.asVector2d(), b.asVector2d())));
    }

    public Vector2d mod(double divisor) {
        return new Vector2d(x%divisor, y%divisor);
    }

    public double distance(Vector2d other) {
        return Math.sqrt(
            Math.pow(this.x-other.x, 2)+
            Math.pow(this.y-other.y, 2)
        );
    }

    // thanks to Sam B from StuyLib for this method
    public Vector2d rotate(Angle angle, Vector2d origin) {
        Vector2d point = this.sub(origin);
        Vector2d out = new Vector2d(
            point.x * angle.cos() - point.y * angle.sin(),
            point.y * angle.cos() + point.x * angle.sin()
        );

        return origin.add(out);
    }

    public Vector2i round() {
        return new Vector2i((int)Math.round(x), (int)Math.round(y));
    }

    public Vector2i ceil() {
        return new Vector2i((int)Math.ceil(x), (int)Math.ceil(y));
    }

    public Vector2i floor() {
        return new Vector2i((int)Math.floor(x), (int)Math.floor(y));
    }

    public boolean equals(Object a) {
        if (!(a instanceof Vector2d))
            return false;
        if (a == this)
            return true;

        Vector2d temp = (Vector2d) a;
        return this.x == temp.x && this.y == temp.y;
    }

    public int hashCode() {
        int hash = 23;
        hash = (int)(hash * 31 + x);
        hash = (int)(hash * 31 + y);
        return hash;
    }

    public String toString() {
        return "("+x+","+y+")";
    }
}
