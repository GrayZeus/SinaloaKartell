import com.google.common.eventbus.EventBus;

public class Network {
    private EventBus eventBus;
    private MSA msa;
    private Base base;

    public Network() {
        eventBus = new EventBus();
        createInstitutions();
        addSubscriber(msa);

    }//end constructor


    private void createInstitutions(){
        msa = new MSA();
        base = new Base(eventBus);
    }

    public void addSubscriber(Subscriber subscriber) {
        eventBus.register(subscriber);
    }

}
