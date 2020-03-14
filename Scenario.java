import java.util.*;
import java.io.*;

public class Scenario {
    public static void main(String[] args) {
        // PointParticle a = new PointParticle(new Vector(1, 0), new Vector(0, 1), 1);
        // PointParticle b = new PointParticle(new Vector(-1, 0), new Vector(0, -1), 1);
        // List<PointParticle> objs = new ArrayList<PointParticle>();
        // objs.add(a);
        // objs.add(b);
        // double t = 0;
        // double dt = 1/30.0;
        // try {
        // BufferedWriter bw = new BufferedWriter(new FileWriter(new File("out.txt")));
        // for (int i = 0; i < 3E6; i++) {
        // Vector pa = a.getPosition();
        // Vector pb = b.getPosition();
        // bw.write(t+","+pa.getX()+","+pa.getY()+","+pb.getX()+","+pb.getY()+"\n");
        // for(PointParticle p : objs)
        // {
        // p.calculateForce(objs);
        // p.move(dt);
        // }
        // t += dt;
        // }
        // bw.close();
        // } catch (Exception e) {
        // // TODO: handle exception
        // }
        // PointParticle a = new PointParticle(new Vector(0, 10), new Vector(1, 2), 1);
        // List<PointParticle> objs = new ArrayList<PointParticle>();
        // objs.add(a);
        // double t = 0;
        // double dt = 1 / 30.0;
        // try {
        //     BufferedWriter bw = new BufferedWriter(new FileWriter(new File("out.txt")));
        //     for (int i = 0; i < (int)(1/dt * 90); i++) {
        //         Vector pa = a.getPosition();
        //         bw.write(t + "," + pa.getX() + "," + pa.getY() + "\n");
        //         a.calculateForce(objs);
        //         a.addForce(new Vector(0, -10));
        //         a.move(dt);
        //         t += dt;
        //     }
        //     bw.close();
        // } catch (Exception e) {
        //     // TODO: handle exception
        // }
        PointParticle a = new PointParticle(new Vector(0, 10), new Vector(1, 2), 1);
        PointParticle b = new PointParticle(new Vector(0, 5), new Vector(1, 2), 1);
        List<PointParticle> objs = new ArrayList<PointParticle>();
        objs.add(a);
        objs.add(b);
        double t = 0;
        double dt = 1 / 30.0;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("out.txt")));
            for (int i = 0; i < (int)(1/dt * 5); i++) {
                bw.write(t + "," + a.getPosition().getX() + "," + a.getPosition().getY() + "," + b.getPosition().getX() + "," + b.getPosition().getY() +"\n");
                for(PointParticle p : objs)
                {
                    p.calculateForce(objs);
                    p.addForce(new Vector(0,-10));
                    p.move(dt);
                }
                t += dt;
            }
            bw.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
