import java.util.HashMap;
import java.util.Map;

public class Protocol<K,V> {
    private Map<K,V> protocolMap;

    public Protocol() {
        protocolMap = new HashMap<>();
    }

    public Map<K, V> getProtocolMap() {
        return protocolMap;
    }

    public void addToProtocolMap(K key, V value) {
        protocolMap.put(key,value);
    }
}
