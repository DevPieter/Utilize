package nl.devpieter.utilize.client.mixin;

import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.network.chat.Component;
import nl.devpieter.sees.Sees;
import nl.devpieter.utilize.client.events.chat.ChatMessageAddEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ChatComponent.class)
public abstract class ChatHudMixin {

    @Unique
    private final Sees sees = Sees.getSharedInstance();

    @ModifyVariable(
            method = "addMessage(Lnet/minecraft/network/chat/Component;Lnet/minecraft/network/chat/MessageSignature;Lnet/minecraft/client/multiplayer/chat/GuiMessageSource;Lnet/minecraft/client/multiplayer/chat/GuiMessageTag;)V",
            at = @At("HEAD"),
            argsOnly = true,
            name = "contents"
    )
    private Component onAddMessage(Component contents) {
        ChatMessageAddEvent event = new ChatMessageAddEvent(contents);
        Component result = sees.dispatchWithResult(event);

        return event.isCancelled() ? contents : result;
    }
}
