package southwestbronze.findyourfriends;

import java.util.ArrayList;

/**
 * Created by spencerwalton on 2016-02-27.
 */
public class Friend {

    private String fname;
    private double x;
    private double y;
    private int lastactive;
    public Friend(String fname, double x, double y, int lastactive)
    {
        this.fname=fname;
        this.x = x;
        this.y = y;
        this.lastactive = lastactive;
    }


    public double getX()
    {
        return this.x;
    }

    public double getY()
    {
        return this.y;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public String getFname()
    {
        return this.fname;
    }

    public int getLastactive() { return this.lastactive; }
}
