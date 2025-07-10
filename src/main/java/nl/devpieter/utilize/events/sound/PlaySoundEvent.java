package nl.devpieter.utilize.events.sound;

import net.minecraft.client.sound.SoundInstance;
import nl.devpieter.sees.Event.CancelableEventBase;

public class PlaySoundEvent extends CancelableEventBase {

    private final SoundInstance sound;

    public PlaySoundEvent(SoundInstance sound) {
        this.sound = sound;
    }

    public SoundInstance sound() {
        return sound;
    }
}
