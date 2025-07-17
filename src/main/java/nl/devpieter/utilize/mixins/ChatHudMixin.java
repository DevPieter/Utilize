package nl.devpieter.utilize.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.Text;
import nl.devpieter.sees.Sees;
import nl.devpieter.utilize.events.chat.ReceiveMessageEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatHud.class)
public abstract class ChatHudMixin {

    @Shadow
    @Final
    private MinecraftClient client;

    @Shadow
    protected abstract void logChatMessage(ChatHudLine message);

    @Shadow
    protected abstract void addVisibleMessage(ChatHudLine message);

    @Shadow
    protected abstract void addMessage(ChatHudLine message);

    @Unique
    private final Sees sees = Sees.getInstance();

    @Inject(at = @At("HEAD"), method = "addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V", cancellable = true)
    public void onAddMessage(Text message, MessageSignatureData signatureData, MessageIndicator indicator, CallbackInfo ci) {
        ReceiveMessageEvent event = new ReceiveMessageEvent(message); // rename to ChatHudMessageAddEvent, or something similar
        Text result = this.sees.dispatchWithResult(event);

        if (event.isCancelled()) {
            ci.cancel();
            return;
        }

        // TODO - Optimize
        ChatHudLine chatHudLine = new ChatHudLine(this.client.inGameHud.getTicks(), result, signatureData, indicator);
        this.logChatMessage(chatHudLine);
        this.addVisibleMessage(chatHudLine);
        this.addMessage(chatHudLine);

        ci.cancel();
    }
}
