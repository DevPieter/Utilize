package nl.devpieter.utilize.listeners.packet;

import net.minecraft.entity.Entity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.network.packet.s2c.play.EntityTrackerUpdateS2CPacket;
import net.minecraft.village.VillagerData;
import net.minecraft.village.VillagerProfession;
import nl.devpieter.sees.Sees;
import nl.devpieter.utilize.events.packet.ProfessionChangedPacketEvent;
import nl.devpieter.utilize.utils.WorldUtils;

import java.lang.reflect.Type;

public class EntityTrackerUpdatePacketListener implements IPacketListener<EntityTrackerUpdateS2CPacket> {

    private final Sees sees = Sees.getInstance();

    @Override
    public Type getPacketType() {
        return EntityTrackerUpdateS2CPacket.class;
    }

    @Override
    public boolean onPacket(EntityTrackerUpdateS2CPacket packet) {
        Entity entity = WorldUtils.getEntity(packet.id());
        if (!(entity instanceof VillagerEntity villager)) return false;

        for (DataTracker.SerializedEntry<?> entry : packet.trackedValues()) {
            if (!(entry.value() instanceof VillagerData newVillagerData)) continue;

            VillagerProfession previous = villager.getVillagerData().getProfession();
            VillagerProfession current = newVillagerData.getProfession();

            if (previous == current) continue;
            this.sees.call(new ProfessionChangedPacketEvent(villager, previous, current));
        }

        return false;
    }
}
