package nl.devpieter.utilize.managers;

import net.minecraft.client.render.debug.DebugRenderer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DebugManager {

    private static DebugManager INSTANCE;

    private final List<DebugRenderer.Renderer> renderers = new ArrayList<>();

    private DebugManager() {
    }

    public static DebugManager getInstance() {
        if (INSTANCE == null) INSTANCE = new DebugManager();
        return INSTANCE;
    }

    public void addRenderer(@NotNull DebugRenderer.Renderer renderer) {
        if (this.renderers.contains(renderer)) return;
        this.renderers.add(renderer);
    }

    public void removeRenderer(@NotNull DebugRenderer.Renderer renderer) {
        this.renderers.remove(renderer);
    }

    public List<DebugRenderer.Renderer> getRenderers() {
        return this.renderers;
    }
}
