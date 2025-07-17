package nl.devpieter.utilize.events.chat;

import net.minecraft.text.Text;
import nl.devpieter.sees.event.SCancelableEventBase;
import nl.devpieter.sees.event.SReturnableEvent;

public class ReceiveMessageEvent extends SCancelableEventBase implements SReturnableEvent<Text> {

    private Text message;

    public ReceiveMessageEvent(Text message) {
        this.message = message;
    }

    @Override
    public Text getResult() {
        return this.message;
    }

    @Override
    public void setResult(Text message) {
        this.message = message;
    }

    public Text message() {
        return this.message;
    }
}
