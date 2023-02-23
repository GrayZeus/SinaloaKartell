import com.google.common.eventbus.Subscribe;
import java.lang.reflect.Method;


public class Location extends Subscriber{
    private int id;
    private String key;
    private String deliveryRequired = "LOCATION " + id + " XREQUESTXONEHUNDREDX";

    public Location() {

    }

        @Subscribe
        public void receive(EventSendBroadcast eventSendBroadcast) {
            try {
                Method versionMethod = eventSendBroadcast.getClass().getDeclaredMethod("sendBroadcast");
                String result = (String) versionMethod.invoke(eventSendBroadcast);
                System.out.println("receive -> eventSendBroadcast" + result);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
