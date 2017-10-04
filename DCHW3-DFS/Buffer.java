import java.util.Observable;

/**
 * Created by tphadke on 8/29/17.
 */
public class Buffer extends Observable {
    private Message message;

    public Buffer(){
        //Create an empty Buffer
    	message = null;
    }
    public Buffer(Message message) {
        this.message = message;
    }

    public Message  getMessage() {
        return message;
    }

    /**
     * 
     * @param message
     * @param arg is used to keep track of the message sending processor
     */
    public void setMessage(Message message , Processor arg ) {
        this.message = message;
        setChanged();
        notifyObservers(arg);
    }
}
