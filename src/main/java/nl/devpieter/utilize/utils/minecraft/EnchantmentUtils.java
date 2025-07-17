package nl.devpieter.utilize.utils.minecraft;

import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.EnchantmentTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class EnchantmentUtils {

    public static boolean isMaxLevel(@NotNull RegistryEntry<Enchantment> enchantment, int level) {
        return level == enchantment.value().getMaxLevel();
    }

    public static boolean isAboveMaxLevel(@NotNull RegistryEntry<Enchantment> enchantment, int level) {
        return level > enchantment.value().getMaxLevel();
    }

    public static boolean isCurse(@NotNull RegistryEntry<Enchantment> enchantment) {
        return enchantment.isIn(EnchantmentTags.CURSE);
    }

    public static boolean hasEnchantments(ItemStack stack) {
        return EnchantmentHelper.hasEnchantments(stack);
    }

    public static boolean hasEnchantment(@Nullable ItemStack stack, @NotNull RegistryEntry<Enchantment> enchantment) {
        if (stack == null || !hasEnchantments(stack)) return false;

        Set<RegistryEntry<Enchantment>> enchantments = getEnchantments(stack);
        return enchantments.stream().anyMatch(entry -> entry == enchantment);
    }

    public static boolean hasEnchantment(@Nullable ItemStack stack, @NotNull RegistryKey<Enchantment> enchantment) {
        if (stack == null || !hasEnchantments(stack)) return false;

        Set<RegistryEntry<Enchantment>> enchantments = getEnchantments(stack);
        return enchantments.stream().anyMatch(entry -> entry.matchesKey(enchantment));
    }

    public static void forEachEnchantment(@NotNull ItemStack stack, @NotNull Consumer consumer) {
        ItemEnchantmentsComponent component = getComponent(stack);
        Set<RegistryEntry<Enchantment>> enchantments = getEnchantments(stack);

        for (RegistryEntry<Enchantment> enchantment : enchantments) {
            consumer.accept(enchantment, component.getLevel(enchantment));
        }
    }

    public static ItemEnchantmentsComponent getComponent(@NotNull ItemStack stack) {
        return EnchantmentHelper.getEnchantments(stack);
    }

    public static Set<RegistryEntry<Enchantment>> getEnchantments(@NotNull ItemStack stack) {
        return getComponent(stack).getEnchantments();
    }

    public interface Consumer {
        void accept(RegistryEntry<Enchantment> enchantment, int level);
    }
}
