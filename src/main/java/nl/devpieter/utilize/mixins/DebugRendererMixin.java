package nl.devpieter.utilize.mixins;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.debug.DebugRenderer;
import net.minecraft.client.util.math.MatrixStack;
import nl.devpieter.utilize.managers.DebugManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//#if MC>=12102
import net.minecraft.client.render.Frustum;
//#endif

@Mixin(DebugRenderer.class)
public class DebugRendererMixin {

    @Unique
    private final DebugManager debugManager = DebugManager.getInstance();

//    @Inject(at = @At("HEAD"), method = "render")
    //#if MC>=12102
//    private void onRender(MatrixStack matrices, Frustum frustum, VertexConsumerProvider.Immediate vertexConsumers, double cameraX, double cameraY, double cameraZ, CallbackInfo ci) {
    //#else
    //$$ public void render(MatrixStack matrices, VertexConsumerProvider.Immediate vertexConsumers, double cameraX, double cameraY, double cameraZ, CallbackInfo ci) {
    //#endif

//        for (DebugRenderer.Renderer renderer : this.debugManager.getRenderers()) {
//            renderer.render(matrices, vertexConsumers, cameraX, cameraY, cameraZ);
//        }
//    }
}
