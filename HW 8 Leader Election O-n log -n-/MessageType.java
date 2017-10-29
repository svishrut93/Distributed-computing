/**
 * Messages that can be passed
 * @author Sample
 */
public enum  MessageType {
	// Terminate message sent when a processor terminates as the leader
	TERMINATE,
	// Probe message sent at the beginning of each phase
	PROBE,
	// Reply message sent when receiver processor's id is smaller than the id in the message and hop counter >= 2^(phase number)
	REPLY
}