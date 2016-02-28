package southwestbronze.findyourfriends;
import java.util.*;
/**
 * Created by spencerwalton on 2016-02-27.
 */
public class User {

    private String uname;
    private double x;
    private double y;
    private ArrayList<String> friends;

    public User(String uname, double x, double y)
    {
        this.uname=uname;
        this.x = x;
        this.y = y;
        this.friends = new ArrayList<String>();
    }

    public void addFriend(String friend)
    {
        this.friends.add(friend);
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

    public String getUname()
    {
        return this.uname;
    }
}
