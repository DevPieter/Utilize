package nl.devpieter.utilize.mixins;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import nl.devpieter.sees.Sees;
import nl.devpieter.utilize.events.tick.ClientPlayerTickEvent;
import nl.devpieter.utilize.events.tick.ClientPlayerTickTailEvent;
import nl.devpieter.utilize.managers.DamageManager;
import nl.devpieter.utilize.task.TaskManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin extends AbstractClientPlayerEntity {

    @Unique
    private final Sees sees = Sees.getSharedInstance();

    @Unique
    private final DamageManager damageManager = DamageManager.getInstance();

    @Unique
    private final TaskManager taskManager = TaskManager.getInstance();

    public ClientPlayerEntityMixin(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

    @Inject(at = @At("HEAD"), method = "tick")
    private void onTick(CallbackInfo ci) {
        taskManager.tick(TaskManager.TickPhase.PLAYER_HEAD);
        sees.dispatch(new ClientPlayerTickEvent());
    }

    @Inject(at = @At("TAIL"), method = "tick")
    private void onTickTail(CallbackInfo ci) {
        damageManager.tick(getHealth());
        taskManager.tick(TaskManager.TickPhase.PLAYER_TAIL);

        sees.dispatch(new ClientPlayerTickTailEvent());
    }
}
