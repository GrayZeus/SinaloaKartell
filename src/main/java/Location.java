import com.google.common.eventbus.Subscribe;
import java.lang.reflect.Method;


public class Location extends Subscriber{

    private RSAKey privateKey;
    private String deliveryRequired = "LOCATIONX" +  "XREQUESTXONEHUNDREDX";

    public Location(String locationID, RSAKey privateKey) {
        this.privateKey = privateKey;

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
