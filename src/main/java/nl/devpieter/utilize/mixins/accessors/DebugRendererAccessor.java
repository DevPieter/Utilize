package nl.devpieter.utilize.mixins.accessors;

import net.minecraft.client.render.debug.DebugRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(DebugRenderer.class)
public interface DebugRendererAccessor {

    @Accessor("debugRenderers")
    List<DebugRenderer.Renderer> utilize$getDebugRenderers();

    @Accessor("lateDebugRenderers")
    List<DebugRenderer.Renderer> utilize$getLateDebugRenderers();
}
