package nl.devpieter.utilize.mixins;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import nl.devpieter.sees.Sees;
import nl.devpieter.utilize.Utilize;
import nl.devpieter.utilize.events.tick.ClientTickEvent;
import nl.devpieter.utilize.managers.DamageManager;
import nl.devpieter.utilize.managers.SleepManager;
import nl.devpieter.utilize.task.TaskManager;
import nl.devpieter.utilize.managers.TotemManager;
import nl.devpieter.utilize.setting.SettingManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin extends AbstractClientPlayerEntity {

    @Unique
    private final Sees sees = Sees.getInstance();

    @Unique
    private final DamageManager damageManager = DamageManager.getInstance();

    @Unique
    private final SleepManager sleepManager = SleepManager.getInstance();

    @Unique
    private final TaskManager taskManager = TaskManager.getInstance();

    @Unique
    private final TotemManager totemManager = TotemManager.getInstance();

    public ClientPlayerEntityMixin(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

    @Inject(at = @At("HEAD"), method = "tick")
    private void onTick(CallbackInfo ci) {
        this.sees.dispatch(new ClientTickEvent()); // rename to ClientPlayerTickEvent, or something similar
    }

    @Inject(at = @At("TAIL"), method = "tick")
    private void onTickTail(CallbackInfo ci) {
        this.damageManager.tick(this.getHealth());
        this.sleepManager.tick(this.isSleeping(), this.getSleepTimer());
        this.taskManager.tick();
        this.totemManager.tick();

        // this.sees.call(new ClientTickEvent()); // rename to ClientPlayerTickTailEvent, or something similar
    }

//    @Inject(at = @At("HEAD"), method = "swingHand", cancellable = true)
//    private void onSwingHand(CallbackInfo ci) {
//        if (!Utilize.shouldBlockSwingHandOnce()) return;
//
//        ci.cancel();
//        Utilize.blockedSwingHandOnce();
//    }
}
