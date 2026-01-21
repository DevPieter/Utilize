package nl.devpieter.utilize.managers;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.debug.DebugRenderer;
import nl.devpieter.utilize.mixins.accessors.DebugRendererAccessor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class DebugManager {

    private static final DebugManager INSTANCE = new DebugManager();

    private final MinecraftClient minecraftClient = MinecraftClient.getInstance();

    private DebugManager() {
    }

    public static DebugManager getInstance() {
        return INSTANCE;
    }

    public void addRenderer(@NotNull DebugRenderer.Renderer renderer) {
        List<DebugRenderer.Renderer> renderers = getAccessor().utilize$getDebugRenderers();
        if (renderers.contains(renderer)) return;

        renderers.add(renderer);
    }

    public void removeRenderer(@NotNull DebugRenderer.Renderer renderer) {
        List<DebugRenderer.Renderer> renderers = getAccessor().utilize$getDebugRenderers();
        renderers.remove(renderer);
    }

    private DebugRendererAccessor getAccessor() {
        return (DebugRendererAccessor) minecraftClient.worldRenderer.debugRenderer;
    }
}
