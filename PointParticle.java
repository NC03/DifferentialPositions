import java.util.*;

public class PointParticle
{
    private Vector position;
    private Vector velocity;
    private Vector force;
    private double mass;

    public PointParticle(Vector position, Vector velocity, double mass)
    {
        this.position = position;
        this.velocity = velocity;
        this.mass = mass;
    }

    public void calculateForce(List<PointParticle> particles)
    {
        force = new Vector(0,0);
        for(PointParticle p : particles)
        {
            if(p != this)
            {
                Vector r = Vector.subtract(p.getPosition(),getPosition());
                Vector f = r.unitVector().scalar(getMass()* p.getMass()/r.magnitude()/r.magnitude());
                force = force.add(f);
            }
        }
    }

    public double getMass()
    {
        return mass;
    }
    
    public Vector getPosition()
    {
        return position;
    }

    public void move(double dt)
    {
        Vector acceleration = force.scalar(1/mass);
        velocity = velocity.add(acceleration.scalar(dt));
        position = position.add(velocity.scalar(dt));
    }
}