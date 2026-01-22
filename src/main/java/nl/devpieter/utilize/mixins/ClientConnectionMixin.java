package nl.devpieter.utilize.mixins;

import net.minecraft.network.ClientConnection;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.Packet;
import nl.devpieter.utilize.managers.PacketManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {

    @Unique
    private static final PacketManager packetManager = PacketManager.getInstance();

    @Inject(at = @At("HEAD"), method = "handlePacket", cancellable = true)
    private static <T extends PacketListener> void onHandlePacket(Packet<T> packet, PacketListener listener, CallbackInfo ci) {
        if (packetManager.packetReceived(packet)) ci.cancel();
    }
}
