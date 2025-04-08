package nl.devpieter.utilize.events.packet;

import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.village.VillagerProfession;
import nl.devpieter.sees.Event.Event;
import org.jetbrains.annotations.NotNull;

public record ProfessionChangedPacketEvent(@NotNull VillagerEntity villager, @NotNull VillagerProfession previous, @NotNull VillagerProfession current) implements Event {
}
