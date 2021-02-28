package bglib.util;

public class Complexd {

    public double a, b;

    public Complexd(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public Complexd(double ab) {
        this(ab, ab);
    }

    public Complexd(Complexd copy) {
        this(copy.a, copy.b);
    }



    public Complexd setA(double a) {
        this.a = a;
        return this;
    }

    public Complexd setB(double b) {
        this.b = b;
        return this;
    }

    public Complexd add(Complexd point) {
        return new Complexd(
            a+point.a,
            b+point.b
        );
    }

    public Complexd sub(Complexd point) {
        return new Complexd(
            a-point.a,
            b-point.b
        );
    }

    public Complexd mul(Complexd point) {
        /* (a1+b1*i)(a2+b2*i)
         = (a1)(a2) + (a1)(b2*i) + (a2)(b1*i) + (b1)(b2)(-1)*/
        return new Complexd(
            this.a*point.a-this.b*point.b,
            this.a*point.b+this.b*point.a
        );
    }

    public double mulConjugate() {
        return mul(getConjugate()).a;
    }

    public Complexd div(double divisor) {
        return new Complexd(
            this.a/divisor,
            this.b/divisor
        );
    }

    public Complexd div(Complexd divisor) {
        /*  this     conjugate(divisor)
           ------- * ------------------
           divisor   conjugate(divisor) */
        return mul(divisor.getConjugate()).div(divisor.mulConjugate());
    }

    public Complexd getConjugate() {
        return new Complexd(this.a, -this.b);
    }

    public double getDistance() {
        return Math.sqrt(a*a+b*b);
    }

    public Vector2d asVector2d() {
        return new Vector2d(this.a, this.b);
    }

    public boolean equals(Object a) {
        if (!(a instanceof Complexd))
            return false;
        if (a == this)
            return true;

        Complexd temp = (Complexd) a;
        return this.a == temp.a && this.b == temp.b;
    }

    public int hashCode() {
        int hash = 23;
        hash = (int)(hash * 31 + a);
        hash = (int)(hash * 31 + b);
        return hash;
    }

    public String toString() {
        return "("+a+", "+b+"i)";
    }

}