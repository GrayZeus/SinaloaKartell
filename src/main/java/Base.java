import com.google.common.eventbus.EventBus;
import jdk.jfr.Event;

import java.util.ArrayList;


public class Base {
    private final EventBus eventBus;
    private String key;
    private Location[] locations = new Location[20];


    public static void main(String... args){
        Base base = new Base();



    }




    public Base() {
        this.eventBus = new EventBus();
            locations[0] = new Location("ONE");
            locations[1] = new Location("TWO");
            locations[2] = new Location("THREE");
            locations[3] = new Location("FOUR");
            locations[4] = new Location("FIVE");
            locations[5] = new Location("SIX");
            locations[6] = new Location("SEVEN");
            locations[7] = new Location("EIGHT");
            locations[8] = new Location("NINE");
            locations[9] = new Location("TEN");
            locations[10] = new Location("ELEVEN");
            locations[11] = new Location("TWELVE");
            locations[12] = new Location("THIRTEEN");
            locations[13] = new Location("FOURTEEN");
            locations[14] = new Location("FIFTEEN");
            locations[15] = new Location("SIXTEEN");
            locations[16] = new Location("SEVENTEEN");
            locations[17] = new Location("EIGHTEEN");
            locations[18] = new Location("NINETEEN");
            locations[19] = new Location("TWENTY");
    }//end constructor

    public void addSubscriber(Subscriber subscriber) {
        eventBus.register(subscriber);
    }

    public void sendBroadcastMessage() {
        eventBus.post(new EventSendBroadcast());
    }

    public void sendDrugs() {
        eventBus.post(new EventSendDrugs());
    }
}
