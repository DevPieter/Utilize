package nl.devpieter.utilize.mixins;

import net.minecraft.text.Text;
import nl.devpieter.sees.Sees;
import nl.devpieter.sees.event.SEvent;
import nl.devpieter.utilize.events.tick.ITickEvent;
import nl.devpieter.utilize.utils.minecraft.ClientUtils;
import nl.devpieter.utilize.utils.minecraft.PlayerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Sees.class, remap = false)
public class SeesMixin {

    @Unique
    private final Logger logger = LoggerFactory.getLogger("Utilize/SeesMixin");

    @Inject(
            method = "dispatch(Lnl/devpieter/sees/event/SEvent;)Z",
            at = @At("RETURN")
    )
    private void onDispatch(SEvent event, CallbackInfoReturnable<Boolean> cir) {
        if (event instanceof ITickEvent) return;
        if (!ClientUtils.isDevEnv()) return;

        this.logger.info("Dispatched event: {} (canceled: {})", event.getClass().getSimpleName(), cir.getReturnValue());
    }
}
