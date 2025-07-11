package nl.devpieter.utilize.events.chat;

import net.minecraft.text.Text;
import nl.devpieter.sees.Event.CancelableEventBase;
import nl.devpieter.sees.Event.ReturnableEvent;

/**
 * Event fired when a chat message ({@link Text}) is received by the client.
 * <p>
 * This event is cancelable and allows modification of the message before display.
 */
public class ReceiveMessageEvent extends CancelableEventBase implements ReturnableEvent<Text> {

    /**
     * The chat message, which may be modified by event listeners.
     */
    private Text message;

    /**
     * Constructs a new ReceiveMessageEvent with the given message.
     *
     * @param message The received chat message.
     */
    public ReceiveMessageEvent(Text message) {
        this.message = message;
    }

    /**
     * Gets the current message result.
     *
     * @return The current chat message.
     */
    @Override
    public Text getResult() {
        return this.message;
    }

    /**
     * Sets the message result.
     *
     * @param message The new chat message to set.
     */
    @Override
    public void setResult(Text message) {
        this.message = message;
    }

    /**
     * Alias for {@link #getResult()}.
     *
     * @return The chat message.
     */
    public Text message() {
        return this.message;
    }
}
