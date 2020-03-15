import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.FontMetrics;

public class ImageGenerator {

    public static void main(String[] args) {
        try {
            File in = new File(args[0]);
            BufferedReader br = new BufferedReader(new FileReader(in));

            List<String> lines = new ArrayList<String>();

            String str = br.readLine();
            int idx = 0;
            while (str != null) {
                lines.add(str);
                str = br.readLine();
            }
            double minx = 0, maxx = 0, miny = 0, maxy = 0.0;

            for (String s : lines) {
                String[] d = s.split(",");
                for (int i = 0; i < d.length / 2; i++) {
                    double x = Double.parseDouble(d[2 * i + 1]);
                    double y = Double.parseDouble(d[2 * i + 2]);
                    if (x < minx)
                        minx = x;
                    else if (x > maxx)
                        maxx = x;
                    if (y < miny)
                        miny = y;
                    else if (y > maxy)
                        maxy = y;
                }
            }

            double width = maxx - minx;
            double height = maxy - miny;
            double avgx = (maxx + minx) / 2;
            double avgy = (maxy + miny) / 2;

            for (int i = 0; i < lines.size(); i++) {
                System.out.println(i + 1 + "/" + lines.size());
                genImage("genImage/" + format(i, (lines.size() - 1 + "").length()) + ".png", lines.get(i),
                        avgx - width / 2 * 1.2, avgx + width / 2 * 1.2, avgy - height / 2 * 1.2,
                        avgy + height / 2 * 1.2, new Color[] { new Color(255, 255, 0), new Color(0, 0, 255), new Color(0, 0, 255), new Color(0, 0, 255), new Color(0, 0, 255) });
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void genImage(String dir, String data, double minx, double maxx, double miny, double maxy) {
        int len = data.split(",").length / 2;
        Color[] colors = new Color[len];
        for (int i = 0; i < len; i++) {
            colors[i] = new Color(0, 0, 0);
        }
        genImage(dir, data, minx, maxx, miny, maxy, colors);
    }

    public static void genImage(String dir, String data, double minx, double maxx, double miny, double maxy,
            Color[] colors) {
        Color background = new Color(255, 255, 255);
        Color axes = new Color(0, 0, 0);

        try {
            BufferedImage bi = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_RGB);
            String[] d = data.split(",");
            Graphics2D g = (Graphics2D) bi.getGraphics();
            g.setColor(background);
            g.fillRect(0, 0, bi.getWidth(), bi.getHeight());
            g.setColor(axes);
            g.drawLine(0, (int)linearInterpolation(miny, maxy, bi.getHeight()-1, 0, 0), bi.getWidth(), (int)linearInterpolation(miny, maxy, bi.getHeight()-1, 0, 0));
            g.drawLine((int)linearInterpolation(minx, maxx, 0,bi.getWidth()-1, 0), 0, (int)linearInterpolation(minx, maxx, 0,bi.getWidth()-1, 0), bi.getHeight());
            String time = "t = " + Math.round(Double.parseDouble(d[0]) * 100) / 100.0 + "s";
            g.drawString(time, bi.getWidth() / 2 - g.getFontMetrics().stringWidth(time) / 2, bi.getHeight() * 8 / 10);
            for (int i = 0; i < d.length / 2; i++) {
                double x = Double.parseDouble(d[2 * i + 1]);
                double y = Double.parseDouble(d[2 * i + 2]);
                x = linearInterpolation(minx, maxx, 0, bi.getWidth() - 1, x);
                y = linearInterpolation(miny, maxy, bi.getHeight() - 1, 0, y);
                g.setColor(colors[i]);
                g.fillOval((int) x - 5, (int) y - 5, 10, 10);
                g.setColor(new Color(255, 0, 0));
                g.fillOval((int) x - 2, (int) y - 2, 4, 4);
            }

            ImageIO.write(bi, "png", new File(dir));
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static double linearInterpolation(double omin, double omax, double nmin, double nmax, double val) {
        return (val - omin) / (omax - omin) * (nmax - nmin) + nmin;
    }

    public static String format(int num, int len) {
        String out = "" + num;
        while (out.length() < len) {
            out = "0" + out;
        }
        return out;
    }

}
