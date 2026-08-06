package nl.devpieter.utilize.client.events.interaction.keybinding;

import nl.devpieter.sees.event.SReturnableEvent;
import nl.devpieter.utilize.client.enums.KeyActionOverride;
import org.jetbrains.annotations.NotNull;

public class KeyBindingPressedCheckEvent implements SReturnableEvent<KeyActionOverride> {

    private final String keyId;
    private KeyActionOverride override = KeyActionOverride.NONE;

    public KeyBindingPressedCheckEvent(@NotNull String keyId) {
        this.keyId = keyId;
    }

    public @NotNull String getKeyId() {
        return keyId;
    }

    @Override
    public KeyActionOverride getResult() {
        return override;
    }

    @Override
    public void setResult(KeyActionOverride result) {
        override = result;
    }
}
