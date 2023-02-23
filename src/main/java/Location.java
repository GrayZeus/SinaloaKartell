import com.google.common.eventbus.Subscribe;
import java.lang.reflect.Method;


public class Location extends Subscriber{

    private String key;
    private String deliveryRequired = "LOCATIONX" +  "XREQUESTXONEHUNDREDX";

    public Location(String number) {
        super();

    }


    @Subscribe
        public void receive(EventSendBroadcast eventSendBroadcast) {
            try {
                System.out.println("receive -> eventSendBroadcast");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
