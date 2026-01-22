package nl.devpieter.utilize.events.interaction.item;

import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import nl.devpieter.sees.event.SReturnableEvent;

public class InteractItemReturnEvent implements SReturnableEvent<ActionResult> {

    private final Hand hand;
    private ActionResult returnValue;

    public InteractItemReturnEvent(Hand hand, ActionResult returnValue) {
        this.hand = hand;
        this.returnValue = returnValue;
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
