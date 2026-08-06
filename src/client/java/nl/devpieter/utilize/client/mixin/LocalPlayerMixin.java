package nl.devpieter.utilize.client.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import nl.devpieter.sees.Sees;
import nl.devpieter.utilize.client.events.tick.ClientPlayerTickEvent;
import nl.devpieter.utilize.client.events.tick.ClientPlayerTickTailEvent;
import nl.devpieter.utilize.client.managers.DamageManager;
import nl.devpieter.utilize.client.task.TaskManager;
import nl.devpieter.utilize.client.task.enums.TickPhase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin extends AbstractClientPlayer {

    @Unique
    private final Sees sees = Sees.getSharedInstance();

    @Unique
    private final DamageManager damageManager = DamageManager.getInstance();

    @Unique
    private final TaskManager taskManager = TaskManager.getInstance();

    public LocalPlayerMixin(ClientLevel level, GameProfile gameProfile) {
        super(level, gameProfile);
    }

    @Inject(at = @At("HEAD"), method = "tick")
    private void onTick(CallbackInfo ci) {
        taskManager.tick(TickPhase.PLAYER_HEAD);
        sees.dispatch(new ClientPlayerTickEvent());
    }

    @Inject(at = @At("TAIL"), method = "tick")
    private void onTickTail(CallbackInfo ci) {
        damageManager.tick(getHealth());
        taskManager.tick(TickPhase.PLAYER_TAIL);

        sees.dispatch(new ClientPlayerTickTailEvent());
    }
}
