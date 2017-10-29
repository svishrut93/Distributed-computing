import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

/**
 * Observable Buffer of each processor
 */
public class Buffer extends Observable {

    private List<Message> messages;

    /**
     * Creates empty buffer
     */
    public Buffer() {
        messages = Collections.synchronizedList(new ArrayList<>());
    }

    /**
     * Sets the message and notifies the observers with the sender node's information
     *
     * @param message Message to be stored in the buffer
     */
    public void saveMessage(Message message) {
        messages.add(message);
        setChanged();
        notifyObservers(message);
    }
}