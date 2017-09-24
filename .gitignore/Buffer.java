import java.util.Observable;


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

    public void setMessage(Message message , Processor arg ) {
        this.message = message;
        setChanged();
        notifyObservers(arg);
    }
}

