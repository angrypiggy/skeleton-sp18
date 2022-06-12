public class NBody 
{
    public static void main(String[] args)
    {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        int numOfPlanets = planets.length;

        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0, 0, "images/starfield.jpg");
        StdDraw.enableDoubleBuffering();

        for (Planet p : planets)
        {
            p.draw();
        }

        double t = 0;
        while (t < T)
        {
            double[] xForces = new double[numOfPlanets];
            double[] yForces = new double[numOfPlanets];;

            // calculate the net xy forces on each planet
            for (int i = 0; i < numOfPlanets; i++)
            {
                // construct the array except itself
                Planet[] otherPlanets = new Planet[numOfPlanets - 1];
                int idx = 0;
                for (Planet p : planets)
                {
                    if (!planets[i].equals(p))
                    {
                        otherPlanets[idx] = p;
                        idx += 1;
                    }
                }
                xForces[i] = planets[i].calcNetForceExertedByX(otherPlanets);
                yForces[i] = planets[i].calcNetForceExertedByY(otherPlanets);
            }

            for (int j = 0; j < numOfPlanets; j++)
            {
                planets[j].update(dt, xForces[j], yForces[j]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet p : planets)
            {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            
            t += dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) 
        {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }

    }


    public static double readRadius(String fileName)
    {
        In in = new In(fileName);
        int numOfPlanets = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String fileName)
    {
        In in = new In(fileName);
        int numOfPlanets = in.readInt();
        double radius = in.readDouble();

        Planet[] res = new Planet[numOfPlanets];
        for (int i = 0; i < numOfPlanets; i++)
        {
            // read attributes
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString(); // "images/" +

            res[i] = new Planet(xP, yP, xV, yV, m, img);
        }
        return res;
    }
}
