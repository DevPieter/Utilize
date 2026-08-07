package nl.devpieter.utilize.client.events.chat;

import net.minecraft.network.chat.Component;
import nl.devpieter.sees.event.SCancelableEventBase;
import nl.devpieter.sees.event.SReturnableEvent;

public class ChatMessageAddEvent extends SCancelableEventBase implements SReturnableEvent<Component> {

    private Component message;

    public ChatMessageAddEvent(Component message) {
        this.message = message;
    }

    @Override
    public Component getResult() {
        return this.message;
    }

    @Override
    public void setResult(Component message) {
        this.message = message;
    }

    public Component message() {
        return this.message;
    }
}
