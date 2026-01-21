package nl.devpieter.utilize.managers;

import net.minecraft.client.render.debug.DebugRenderer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class DebugManager {

    private static final DebugManager INSTANCE = new DebugManager();

    private final List<DebugRenderer.Renderer> renderers = new ArrayList<>();

    private DebugManager() {
    }

    public static DebugManager getInstance() {
        return INSTANCE;
    }

    public void addRenderer(@NotNull DebugRenderer.Renderer renderer) {
        if (renderers.contains(renderer)) return;
        renderers.add(renderer);
    }

    public void removeRenderer(@NotNull DebugRenderer.Renderer renderer) {
        renderers.remove(renderer);
    }

    public List<DebugRenderer.Renderer> getRenderers() {
        return renderers;
    }
}
