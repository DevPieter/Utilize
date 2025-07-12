package nl.devpieter.utilize.mixins;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import nl.devpieter.sees.Sees;
import nl.devpieter.utilize.Utilize;
import nl.devpieter.utilize.events.interaction.AttackEntityEvent;
import nl.devpieter.utilize.events.interaction.InteractEntityEvent;
import nl.devpieter.utilize.events.interaction.InteractItemEvent;
import nl.devpieter.utilize.events.interaction.UpdateBlockBreakingProgressEvent;
import nl.devpieter.utilize.events.interaction.block.*;
import nl.devpieter.utilize.events.inventory.HotbarSlotChangedEvent;
import nl.devpieter.utilize.events.inventory.SlotClickEvent;
import nl.devpieter.utilize.utils.InventoryUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public abstract class ClientPlayerInteractionManagerMixin {

    @Shadow
    public abstract void cancelBlockBreaking();

    @Unique
    private int lastSelectedHotbarSlot = -1;

    @Unique
    private final Sees sees = Sees.getInstance();

    @Inject(at = @At("HEAD"), method = "updateBlockBreakingProgress", cancellable = true)
    private void onUpdateBlockBreakingProgress(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        if (!this.sees.dispatch(new UpdateBlockBreakingProgressEvent(pos, direction))) return;

        // TODO - Make not cancelable?

        this.cancelBlockBreaking();
        cir.setReturnValue(false);
    }

    @Inject(at = @At("HEAD"), method = "breakBlock", cancellable = true)
    private void onBreakBlock(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (!this.sees.dispatch(new BreakBlockEvent(pos))) return;

        this.cancelBlockBreaking();
        cir.setReturnValue(false);
    }

    @Inject(at = @At("RETURN"), method = "breakBlock", cancellable = true)
    private void onBreakBlockReturn(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(this.sees.dispatchWithResult(new BreakBlockReturnEvent(pos, cir.getReturnValue())));
    }

    @Inject(at = @At("TAIL"), method = "breakBlock")
    private void onBreakBlockTail(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        this.sees.dispatch(new BreakBlockTailEvent(pos));
    }

    @Inject(at = @At("HEAD"), method = "attackBlock", cancellable = true)
    private void onAttackBlock(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        if (!this.sees.dispatch(new AttackBlockEvent(pos, direction))) return;

        this.cancelBlockBreaking();
        cir.setReturnValue(false);
    }

    @Inject(at = @At("RETURN"), method = "attackBlock", cancellable = true)
    private void onAttackBlockReturn(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(this.sees.dispatchWithResult(new AttackBlockReturnEvent(pos, direction, cir.getReturnValue())));
    }

    @Inject(at = @At("TAIL"), method = "attackBlock")
    private void onAttackBlockTail(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        this.sees.dispatch(new AttackBlockTailEvent(pos, direction));
    }

    @Inject(at = @At("HEAD"), method = "interactBlock", cancellable = true)
    private void onInteractBlock(ClientPlayerEntity player, Hand hand, BlockHitResult hitResult, CallbackInfoReturnable<ActionResult> cir) {
        if (!this.sees.dispatch(new InteractBlockEvent(hand, hitResult))) return;
        cir.setReturnValue(ActionResult.FAIL);
    }

    @Inject(at = @At("RETURN"), method = "interactBlock", cancellable = true)
    private void onInteractBlockReturn(ClientPlayerEntity player, Hand hand, BlockHitResult hitResult, CallbackInfoReturnable<ActionResult> cir) {
        cir.setReturnValue(this.sees.dispatchWithResult(new InteractBlockReturnEvent(hand, hitResult, cir.getReturnValue())));
    }

    @Inject(at = @At("TAIL"), method = "interactBlock")
    private void onInteractBlockTail(ClientPlayerEntity player, Hand hand, BlockHitResult hitResult, CallbackInfoReturnable<ActionResult> cir) {
        this.sees.dispatch(new InteractBlockTailEvent(hand, hitResult));
    }

    @Inject(at = @At("HEAD"), method = "interactItem", cancellable = true)
    private void onInteractItem(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (!this.sees.dispatch(new InteractItemEvent(hand))) return;
        cir.setReturnValue(ActionResult.FAIL);
        cir.cancel();
    }

    @Inject(at = @At("HEAD"), method = "attackEntity", cancellable = true)
    private void onAttackEntity(PlayerEntity player, Entity target, CallbackInfo ci) {
        System.out.println("AttackEntityEvent called for " + target.getName().getString() + " by " + player.getName().getString());
        if (!this.sees.dispatch(new AttackEntityEvent(target))) return;

        ci.cancel();
        Utilize.blockSwingHandOnce();
    }

    @Inject(at = @At("HEAD"), method = "interactEntity", cancellable = true)
    private void onInteractEntity(PlayerEntity player, Entity target, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (!this.sees.dispatch(new InteractEntityEvent(target, hand))) return;
        cir.setReturnValue(ActionResult.FAIL);
        cir.cancel();
    }

    @Inject(at = @At("HEAD"), method = "clickSlot", cancellable = true)
    private void onClickSlot(int syncId, int slotId, int button, SlotActionType actionType, PlayerEntity player, CallbackInfo ci) {
        if (!this.sees.dispatch(new SlotClickEvent(syncId, slotId, button, actionType))) return;
        ci.cancel();
    }

    @Inject(at = @At("TAIL"), method = "syncSelectedSlot")
    private void onSyncSelectedSlot(CallbackInfo ci) {
        int slot = InventoryUtils.getSelectedHotbarSlot();

        if (this.lastSelectedHotbarSlot == -1) {
            this.lastSelectedHotbarSlot = slot;
            return;
        }

        if (slot == this.lastSelectedHotbarSlot) return;
        this.lastSelectedHotbarSlot = slot;

        this.sees.dispatch(new HotbarSlotChangedEvent(this.lastSelectedHotbarSlot, slot));
    }
}
