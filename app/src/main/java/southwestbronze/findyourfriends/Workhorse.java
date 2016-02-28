package southwestbronze.findyourfriends;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by spencerwalton on 16-02-27.
 */
public class Workhorse {

    public static User currUser;

    public static void addFriend(String fname)
    {
        Random rand = new Random();
        int xmul = 1;
        int ymul = 1;
        if(rand.nextBoolean())
            xmul = -1;
        if(rand.nextBoolean())
            ymul = -1;

        currUser.addFriend(new Friend(fname, currUser.getX()+xmul*rand.nextDouble()/10, currUser.getY()+ymul*rand.nextDouble()/10, 10));

    }

    public static void startUp(LatLng l)
    {
        currUser = new User("jim",l.latitude,l.longitude);
        addFriend("bob");
        addFriend("geoff");
        addFriend("jeff");
        addFriend("nickholas");
        addFriend("gurwan");

    }
    public static ArrayList<Friend> getFriends()
    {
        return currUser.getFriends();
    }


}
