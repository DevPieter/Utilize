package nl.devpieter.utilize.events.interaction.block;

import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import nl.devpieter.sees.event.SReturnableEvent;

public class InteractBlockReturnEvent implements SReturnableEvent<ActionResult> {

    private final Hand hand;
    private final BlockHitResult hitResult;
    private ActionResult returnValue;

    public InteractBlockReturnEvent(Hand hand, BlockHitResult hitResult, ActionResult returnValue) {
        this.hand = hand;
        this.hitResult = hitResult;
        this.returnValue = returnValue;
    }

    public Hand hand() {
        return this.hand;
    }

    public BlockHitResult hitResult() {
        return this.hitResult;
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
