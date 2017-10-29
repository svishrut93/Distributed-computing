public class Message {
	
	private MessageType messageType;
	private int id;
	private int phaseNum;
	private int hopCount;
	private Processor sender;
	
	/**
	 * Constructor for Probe message
	 * @param mt message type
	 * @param pid processor id
	 * @param pn phase number
	 * @param hc hop count
	 */
	public Message(MessageType mt, int pid, int pn, int hc) {
		messageType = mt;
		id = pid;
		phaseNum = pn;
		hopCount = hc;
	}

	/**
	 * Constructor for Reply message
	 * @param mt message type
	 * @param pid processor id
	 * @param pn phase number
	 */
	public Message(MessageType mt, int pid, int pn) {
		messageType = mt;
		id = pid;
		phaseNum = pn;
	}
	
	/**
	 * Constructor for Terminate message
	 * @param mt message type
	 */
	public Message(MessageType mt) {
		messageType = mt;
	}
	
	public void setSender(Processor p) {
		sender = p;
	}
	
	public Processor getSender() {
		return sender;
	}
	
	public MessageType getMessageType() {
		return messageType;
	}
	
	public int getPID() {
		return id;
	}

	public int getPhaseNum() {
		return phaseNum;
	}

	public int getHopCount() {
		return hopCount;
	}
}