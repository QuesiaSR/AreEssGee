package me.falu.areessgee.mixin.blocks;

import com.google.common.collect.Lists;
import me.falu.areessgee.AreEssGee;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.GravelBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.context.LootContext;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;
import java.util.Random;

@Mixin(GravelBlock.class)
public abstract class GravelBlockMixin extends FallingBlock {
    public GravelBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    @SuppressWarnings("deprecation")
    public List<ItemStack> getDroppedStacks(BlockState state, LootContext.Builder builder) {
        List<ItemStack> original = super.getDroppedStacks(state, builder);
        if (original.isEmpty()) {
            return original;
        }
        int amount = 0;
        if (original.get(0).getItem().equals(Items.FLINT)) {
            amount += original.get(0).getCount() - 1;
        }
        if (new Random().nextFloat() >= AreEssGee.CONFIG.flintRarity) {
            amount++;
        }
        if (amount != 0) {
            return Lists.newArrayList(new ItemStack(Items.FLINT, amount));
        }
        return original;
    }
}
