package southwestbronze.findyourfriends;

/**
 * Created by spencerwalton on 16-02-27.
 */
public class Workhorse {

    public static User currUser;

    public static void addFriend(String fname)
    {
        currUser.addFriend(AzureConnect.getFriend(fname));

    }


}
