public final class Vector
{
    private final double x;
    private final double y;

    public Vector(double x, double y)
    {
        this.x=x;
        this.y=y;
    }

    public double getX()
    {
        return this.x;
    }
    public double getY()
    {
        return this.y;
    }
    public double magnitude()
    {
        return Math.sqrt(x*x+y*y);
    }
    // TODO: Finish/validate atan
    public double angle()
    {
        if(x == 0 && y == 0)
        {
            return 0;
        }else if(x == 0 && y > 0)
        {
            return Math.PI/2;
        }else if(x == 0 && y < 0)
        {
            return Math.PI*3/2;
        }else if(x < 0){
            return Math.atan(y/x)+Math.PI;
        }else{
            return Math.atan(y/x);
        }
    }
    public Vector add(Vector v)
    {
        return add(this,v);
    }
    public Vector subtract(Vector v)
    {
        return subtract(this,v);
    }
    public Vector scalar(double m)
    {
        return new Vector(x*m,y*m);
    }
    public Vector unitVector()
    {
        return scalar(1/magnitude());
    }


    public static Vector add(Vector a, Vector b)
    {
        return new Vector(a.getX()+b.getX(),a.getY()+b.getY());
    }
    public static Vector subtract(Vector a, Vector b)
    {
        return add(a,b.scalar(-1));
    }

    

    
}