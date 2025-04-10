package evannakita.wattledaub.block;

import com.mojang.serialization.MapCodec;

import evannakita.wattledaub.ModBlocks;
import evannakita.wattledaub.ModItems;
import evannakita.wattledaub.enums.DaubLevel;
import evannakita.wattledaub.enums.DaubType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class DaubBlock extends Block {
	public static final MapCodec<DaubBlock> CODEC = createCodec(DaubBlock::new);

	@Override
	public MapCodec<? extends DaubBlock> getCodec() {
		return CODEC;
	}

    public DaubBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {         Block block = state.getBlock();
        DaubType type = this.getType(block);
        DaubLevel level = this.getLevel(block);
        Item item = stack.getItem();
        Block newBlock = block;
        boolean addDaub = false;
        boolean removeDaub = false;
        if (item instanceof ShovelItem) {
            switch (level) {
                case SCATTERED_DAUB:
                    removeDaub = true;
                    newBlock = ModBlocks.WATTLE;
                    break;
                case COVERED_DAUB:
                    removeDaub = true;
                    switch (type) {
                        case CLAY: newBlock = ModBlocks.SCATTERED_CLAY_DAUB; break;
                        case COARSE_CLAY: newBlock = ModBlocks.SCATTERED_COARSE_CLAY_DAUB; break;
                        case MUD: newBlock = ModBlocks.SCATTERED_MUD_DAUB; break;
                        case PACKED_MUD: newBlock = ModBlocks.SCATTERED_PACKED_MUD_DAUB; break;
                        case SAND: newBlock = ModBlocks.SCATTERED_SAND_DAUB; break;
                    }
                    break;
                case DAUB:
                    removeDaub = true;
                    switch (type) {
                        case CLAY: newBlock = ModBlocks.COVERED_CLAY_DAUB; break;
                        case COARSE_CLAY: newBlock = ModBlocks.COVERED_COARSE_CLAY_DAUB; break;
                        case MUD: newBlock = ModBlocks.COVERED_MUD_DAUB; break;
                        case PACKED_MUD: newBlock = ModBlocks.COVERED_PACKED_MUD_DAUB; break;
                        case SAND: newBlock = ModBlocks.COVERED_SAND_DAUB; break;
                    }
                    break;
            }
        } else if (this.getType(item) == type) {
            switch (level) {
                case SCATTERED_DAUB:
                    addDaub = true;
                    switch(type) {
                        case CLAY: newBlock = ModBlocks.COVERED_CLAY_DAUB; break;
                        case COARSE_CLAY: newBlock = ModBlocks.COVERED_COARSE_CLAY_DAUB; break;
                        case MUD: newBlock = ModBlocks.COVERED_MUD_DAUB; break;
                        case PACKED_MUD: newBlock = ModBlocks.COVERED_PACKED_MUD_DAUB; break;
                        case SAND: newBlock = ModBlocks.COVERED_SAND_DAUB; break;
                    }
                    break;
                case COVERED_DAUB:
                    addDaub = true;
                    switch(type) {
                        case CLAY: newBlock = ModBlocks.CLAY_DAUB; break;
                        case COARSE_CLAY: newBlock = ModBlocks.COARSE_CLAY_DAUB; break;
                        case MUD: newBlock = ModBlocks.MUD_DAUB; break;
                        case PACKED_MUD: newBlock = ModBlocks.PACKED_MUD_DAUB; break;
                        case SAND: newBlock = ModBlocks.SAND_DAUB; break;
                    }
                    break;
                case DAUB: break;
            }
        }
        if (newBlock != block){
            if (addDaub)  {
                if (world.isClient) return ItemActionResult.SUCCESS;
                world.playSound(null, pos, SoundEvents.BLOCK_MUD_PLACE, SoundCategory.BLOCKS, 0.75f, 1.0f);
                if (!player.getAbilities().creativeMode) {
                    stack.decrement(1);
                }
            }
            if (removeDaub) {
                if (world.isClient) return ItemActionResult.SUCCESS;
                if (!player.getAbilities().creativeMode) {
                    Item newItem = null;
                    switch(type) {
                        case CLAY: newItem = ModItems.CLAY_DAUB_BALL; break;
                        case COARSE_CLAY: newItem = ModItems.COARSE_CLAY_DAUB_BALL; break;
                        case MUD: newItem = ModItems.MUD_DAUB_BALL; break;
                        case PACKED_MUD: newItem = ModItems.PACKED_MUD_DAUB_BALL; break;
                        case SAND: newItem = ModItems.SAND_DAUB_BALL; break;
                    }
                    Direction direction = hit.getSide();
                    Direction direction2 = direction.getAxis() == Direction.Axis.Y ? player.getHorizontalFacing().getOpposite() : direction;
                    ItemEntity itemEntity = new ItemEntity(
                        world,
                        (double)pos.getX() + 0.5 + (double)direction2.getOffsetX() * 0.65,
                        (double)pos.getY() + 0.1,
                        (double)pos.getZ() + 0.5 + (double)direction2.getOffsetZ() * 0.65,
                        new ItemStack(newItem, 1)
                    );
                    itemEntity.setVelocity(
                        0.05 * (double)direction2.getOffsetX() + world.random.nextDouble() * 0.02, 0.05, 0.05 * (double)direction2.getOffsetZ() + world.random.nextDouble() * 0.02
                    );
                    world.spawnEntity(itemEntity);
                    stack.damage(1, player, LivingEntity.getSlotForHand(hand));
                }
                world.playSound(null, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 0.75f, 1.0f);
            }

            BlockState newState;
            if (newBlock instanceof WattleBlock) {
                newState = ((WattleBlock)newBlock).getPlacementState(world, pos);
            } else {
                newState = newBlock.getDefaultState();
            }
            world.setBlockState(pos, newState, Block.NOTIFY_ALL_AND_REDRAW);
            player.incrementStat(Stats.USED.getOrCreateStat(item));
            return ItemActionResult.success(world.isClient);
        }
        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    public DaubLevel getLevel(Block block) {
        if (
            block == ModBlocks.SCATTERED_CLAY_DAUB ||
            block == ModBlocks.SCATTERED_COARSE_CLAY_DAUB ||
            block == ModBlocks.SCATTERED_MUD_DAUB ||
            block == ModBlocks.SCATTERED_PACKED_MUD_DAUB ||
            block == ModBlocks.SCATTERED_SAND_DAUB
        ) {
            return DaubLevel.SCATTERED_DAUB;
        }
        if (
            block == ModBlocks.COVERED_CLAY_DAUB ||
            block == ModBlocks.COVERED_COARSE_CLAY_DAUB ||
            block == ModBlocks.COVERED_MUD_DAUB ||
            block == ModBlocks.COVERED_PACKED_MUD_DAUB ||
            block == ModBlocks.COVERED_SAND_DAUB
        ) {
            return DaubLevel.COVERED_DAUB;
        }
        if (
            block == ModBlocks.CLAY_DAUB ||
            block == ModBlocks.COARSE_CLAY_DAUB ||
            block == ModBlocks.MUD_DAUB ||
            block == ModBlocks.PACKED_MUD_DAUB ||
            block == ModBlocks.SAND_DAUB
        ) {
            return DaubLevel.DAUB;
        }
        return null;
    }

    public DaubType getType(Block block) {
        if (
            block == ModBlocks.SCATTERED_CLAY_DAUB ||
            block == ModBlocks.COVERED_CLAY_DAUB ||
            block == ModBlocks.CLAY_DAUB
        ) {
            return DaubType.CLAY;
        }
        if (
            block == ModBlocks.SCATTERED_COARSE_CLAY_DAUB ||
            block == ModBlocks.COVERED_COARSE_CLAY_DAUB ||
            block == ModBlocks.COARSE_CLAY_DAUB
        ) {
            return DaubType.COARSE_CLAY;
        }
        if (
            block == ModBlocks.SCATTERED_MUD_DAUB ||
            block == ModBlocks.COVERED_MUD_DAUB ||
            block == ModBlocks.MUD_DAUB
        ) {
            return DaubType.MUD;
        }
        if (
            block == ModBlocks.SCATTERED_PACKED_MUD_DAUB ||
            block == ModBlocks.COVERED_PACKED_MUD_DAUB ||
            block == ModBlocks.PACKED_MUD_DAUB
        ) {
            return DaubType.PACKED_MUD;
        }
        if (
            block == ModBlocks.SCATTERED_SAND_DAUB ||
            block == ModBlocks.COVERED_SAND_DAUB ||
            block == ModBlocks.SAND_DAUB
        ) {
            return DaubType.SAND;
        }
        return null;
    }

    public DaubType getType(Item item) {
        if (item == ModItems.CLAY_DAUB_BALL) {
            return DaubType.CLAY;
        };
        if (item == ModItems.COARSE_CLAY_DAUB_BALL) {
            return DaubType.COARSE_CLAY;
        };
        if (item == ModItems.MUD_DAUB_BALL) {
            return DaubType.MUD;
        };
        if (item == ModItems.PACKED_MUD_DAUB_BALL) {
            return DaubType.PACKED_MUD;
        };
        if (item == ModItems.SAND_DAUB_BALL) {
            return DaubType.SAND;
        };
        return null;
    }
}
