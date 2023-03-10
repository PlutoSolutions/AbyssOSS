package me.ciruu.abyss.mixin.client.blocks;

import me.ciruu.abyss.Manager;
import me.ciruu.abyss.modules.movement.NoSlowDown;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSoulSand;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={BlockSoulSand.class})
public class MixinBlockSoulSand
extends Block {
    public MixinBlockSoulSand() {
        super(Material.SAND, MapColor.BROWN);
    }

    @Inject(method={"onEntityCollision"}, at={@At(value="HEAD")}, cancellable=true)
    public void onEntityCollisionHook(World world, BlockPos blockPos, IBlockState iBlockState, Entity entity, CallbackInfo callbackInfo) {
        if (Manager.moduleManager.isModuleEnabled(NoSlowDown.class) && ((Boolean)((NoSlowDown)Manager.moduleManager.getModuleByClass(NoSlowDown.class)).soulsand.getValue()).booleanValue()) {
            callbackInfo.cancel();
        }
    }
}
