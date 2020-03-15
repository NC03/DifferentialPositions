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
        // BufferedWriter bw = new BufferedWriter(new FileWriter(new File("out.txt")));
        // for (int i = 0; i < (int)(1/dt * 90); i++) {
        // Vector pa = a.getPosition();
        // bw.write(t + "," + pa.getX() + "," + pa.getY() + "\n");
        // a.calculateForce(objs);
        // a.addForce(new Vector(0, -10));
        // a.move(dt);
        // t += dt;
        // }
        // bw.close();
        // } catch (Exception e) {
        // // TODO: handle exception
        // }
        // PointParticle a = new PointParticle(new Vector(0, 10), new Vector(1, 2), 1);
        // PointParticle b = new PointParticle(new Vector(0, 5), new Vector(1, 2), 1);
        // List<PointParticle> objs = new ArrayList<PointParticle>();
        // objs.add(a);
        // objs.add(b);
        // double t = 0;
        // double dt = 1 / 30.0;
        // try {
        // BufferedWriter bw = new BufferedWriter(new FileWriter(new File("out.txt")));
        // for (int i = 0; i < (int)(1/dt * 5); i++) {
        // bw.write(t + "," + a.getPosition().getX() + "," + a.getPosition().getY() +
        // "," + b.getPosition().getX() + "," + b.getPosition().getY() +"\n");
        // for(PointParticle p : objs)
        // {
        // p.calculateForce(objs);
        // p.addForce(new Vector(0,-10));
        // p.move(dt);
        // }
        // t += dt;
        // }
        // bw.close();
        // } catch (Exception e) {
        // // TODO: handle exception
        // }
        sunandearth();
    }

    public static void sunandearth() {
        PointParticle sun = new PointParticle(new Vector(0, 0), new Vector(0, 0), 1988500E24);
        PointParticle earth = new PointParticle(new Vector(149.6E9, 0), new Vector(0, 29782.254), 5.9724E24);
        List<PointParticle> objs = new ArrayList<PointParticle>();
        objs.add(sun);
        objs.add(earth);
        double t = 0;
        double dt = 1 / 30.0;
        double duration = 31557600;
        int maxDataPoints = 500;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("out.txt")));

            for (int i = 0; i < (int) (duration / dt); i++) {
                if (i % ((int) (duration / dt) / maxDataPoints) == 0) {
                    bw.write(t + "," + sun.getPosition().getX() + "," + sun.getPosition().getY() + ","
                            + earth.getPosition().getX() + "," + earth.getPosition().getY() + "\n");
                    double percent = i * dt / duration;
                    int width = 16;
                    int len = (int) (width * percent);
                    System.out.print(percent(percent) + "\t[" + rep("#", len) + rep(".", 16 - len) + "]                        \r");
                }
                for (PointParticle p : objs) {
                    p.calculateForce(objs);
                    p.move(dt);
                }
                t += dt;
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String rep(String s, int len) {
        String out = s;
        while (out.length() < len) {
            out += s;
        }
        return out;
    }

    public static String percent(double num){
        String a = ""+(int)(num*100);
        String b = ""+(int)Math.round(num*10000)%100;
        while (a.length() < 2) {
            a = "0"+a;
        }
        while (b.length() < 2) {
            b += "0";
        }
        return a + "." + b+"%";
    }
    public static String format(String s, int len)
    {
        String out = s;
        while (out.length() < len) {
            out = "0"+out;
        }
        return out;
    }
}
