package nl.devpieter.utilize.utils.minecraft;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class PlayerUtils {

    private PlayerUtils() {
    }

    public static void sendMessage(@NotNull Text message, boolean overlay) {
        if (!ClientUtils.hasPlayer()) return;
        ClientUtils.getPlayer().sendMessage(message, overlay);
    }

    public static @Nullable HitResult getHitResult() {
        if (!ClientUtils.hasPlayer()) return null;

        HitResult result = ClientUtils.getClient().crosshairTarget;
        if (!(result instanceof HitResult hitResult)) return null;

        return hitResult;
    }

    public static @Nullable BlockHitResult getBlockHitResult() {
        if (!ClientUtils.hasPlayer()) return null;

        HitResult result = ClientUtils.getClient().crosshairTarget;
        if (!(result instanceof BlockHitResult blockHitResult)) return null;

        return blockHitResult;
    }

    public static @Nullable Block getTargetedBlock() {
        BlockState blockState = getTargetedBlockState();
        if (blockState == null) return null;

        return blockState.getBlock();
    }

    public static @Nullable BlockState getTargetedBlockState() {
        BlockHitResult blockHitResult = getBlockHitResult();
        if (blockHitResult == null) return null;

        return ClientUtils.getWorld().getBlockState(blockHitResult.getBlockPos());
    }
}
