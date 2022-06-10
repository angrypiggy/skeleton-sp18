public class Planet
{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67 * 1e-11;

    public Planet(double xP, double yP, double xV, 
                  double yV, double m, String img)
    {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p)
    {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p)
    {
        double dx = xxPos - p.xxPos;
        double dy = yyPos - p.yyPos;
        double r_sq = dx * dx + dy * dy;
        return Math.sqrt(r_sq);
    }
                  
    public double calcForceExertedBy(Planet p)
    {
        double r = calcDistance(p);
        double F = G * mass * p.mass / (r * r);
        return F;
    }

    public double calcForceExertedByX(Planet p)
    {
        double dx = p.xxPos - xxPos;
        double r = calcDistance(p);
        double F = calcForceExertedBy(p);
        double FX = F * dx / r;
        return FX;
    }

    public double calcForceExertedByY(Planet p)
    {
        double dy = p.yyPos - yyPos;
        double r = calcDistance(p);
        double F = calcForceExertedBy(p);
        double FY = F * dy / r;
        return FY;
    }
}