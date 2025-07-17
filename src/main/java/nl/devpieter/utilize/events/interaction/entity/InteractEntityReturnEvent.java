package nl.devpieter.utilize.events.interaction.entity;

import net.minecraft.entity.Entity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import nl.devpieter.sees.event.SReturnableEvent;

public class InteractEntityReturnEvent implements SReturnableEvent<ActionResult> {

    private final Entity target;
    private final Hand hand;
    private ActionResult returnValue;

    public InteractEntityReturnEvent(Entity target, Hand hand, ActionResult returnValue) {
        this.target = target;
        this.hand = hand;
        this.returnValue = returnValue;
    }

    public Entity target() {
        return this.target;
    }

    public Hand hand() {
        return this.hand;
    }

    @Override
    public ActionResult getResult() {
        return this.returnValue;
    }

    @Override
    public void setResult(ActionResult result) {
        this.returnValue = result;
    }
}
