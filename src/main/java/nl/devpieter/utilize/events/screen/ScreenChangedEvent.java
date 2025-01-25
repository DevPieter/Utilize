package nl.devpieter.utilize.events.screen;

import net.minecraft.client.gui.screen.Screen;
import nl.devpieter.sees.Event.Event;
import org.jetbrains.annotations.Nullable;

public record ScreenChangedEvent(@Nullable Screen previous, @Nullable Screen current) implements Event {
}
