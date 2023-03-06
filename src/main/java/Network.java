import com.google.common.eventbus.EventBus;

public class Network {
    private EventBus eventBus;
    private MSA msa;
    private Base base;

    public Network() {
        eventBus = new EventBus();
        createInstitutions();
        addSubscriber(base);
        addSubscriber(msa);
        base.setEventBus(eventBus);
        base.instantiateLocationsAndAddThemAsSubscribers();
        base.sendPublicKey(base.getPublicKey());
        msa.setLocations(base.getLocations());

        base.getLocations()[7].doOrder();

    }//end constructor


    private void createInstitutions(){
        msa = new MSA();
        base = new Base();
    }

    public void addSubscriber(Subscriber subscriber) {
        eventBus.register(subscriber);
    }

    public Base getBase() {
        return base;
    }

    public MSA getMsa() {
        return msa;
    }
}
