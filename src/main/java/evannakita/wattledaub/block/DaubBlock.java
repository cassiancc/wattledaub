package evannakita.wattledaub.block;

import evannakita.wattledaub.ModBlocks;
import evannakita.wattledaub.ModItems;
import evannakita.wattledaub.enums.DaubLevel;
import evannakita.wattledaub.enums.DaubType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class DaubBlock extends Block {

    public DaubBlock(Settings settings) {
        super(settings);
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        var block = state.getBlock();
        DaubType type = this.getType(block);
        DaubLevel level = this.getLevel(block);
        ItemStack stack = player.getStackInHand(hand);
        Item item = stack.getItem();
        Block newBlock = block;
        boolean addDaub = false;
        boolean removeDaub = false;
        if (item instanceof ShovelItem) {
            newBlock = switch (level) {
                case SCATTERED_DAUB -> {
                    removeDaub = true;
                    yield ModBlocks.WATTLE.get();
                }
                case COVERED_DAUB -> {
                    removeDaub = true;
                    yield switch (type) {
                        case CLAY -> ModBlocks.SCATTERED_CLAY_DAUB.get();
                        case COARSE_CLAY -> ModBlocks.SCATTERED_COARSE_CLAY_DAUB.get();
                        case MUD -> ModBlocks.SCATTERED_MUD_DAUB.get();
                        case PACKED_MUD -> ModBlocks.SCATTERED_PACKED_MUD_DAUB.get();
                        case SAND -> ModBlocks.SCATTERED_SAND_DAUB.get();
                    };
                }
                case DAUB -> {
                    removeDaub = true;
                    yield switch (type) {
                        case CLAY -> ModBlocks.COVERED_CLAY_DAUB.get();
                        case COARSE_CLAY -> ModBlocks.COVERED_COARSE_CLAY_DAUB.get();
                        case MUD -> ModBlocks.COVERED_MUD_DAUB.get();
                        case PACKED_MUD -> ModBlocks.COVERED_PACKED_MUD_DAUB.get();
                        case SAND -> ModBlocks.COVERED_SAND_DAUB.get();
                    };
                }
            };
        } else if (this.getType(item) == type) {
            switch (level) {
                case SCATTERED_DAUB:
                    addDaub = true;
                    newBlock = switch (type) {
                        case CLAY -> ModBlocks.COVERED_CLAY_DAUB.get();
                        case COARSE_CLAY -> ModBlocks.COVERED_COARSE_CLAY_DAUB.get();
                        case MUD -> ModBlocks.COVERED_MUD_DAUB.get();
                        case PACKED_MUD -> ModBlocks.COVERED_PACKED_MUD_DAUB.get();
                        case SAND -> ModBlocks.COVERED_SAND_DAUB.get();
                    };
                    break;
                case COVERED_DAUB:
                    addDaub = true;
                    newBlock = switch (type) {
                        case CLAY -> ModBlocks.CLAY_DAUB.get();
                        case COARSE_CLAY -> ModBlocks.COARSE_CLAY_DAUB.get();
                        case MUD -> ModBlocks.MUD_DAUB.get();
                        case PACKED_MUD -> ModBlocks.PACKED_MUD_DAUB.get();
                        case SAND -> ModBlocks.SAND_DAUB.get();
                    };
                    break;
                case DAUB: break;
            }
        }
        if (newBlock != block){
            if (addDaub)  {
                if (world.isClient) return ActionResult.SUCCESS;
                world.playSound(null, pos, SoundEvents.BLOCK_MUD_PLACE, SoundCategory.BLOCKS, 0.75f, 1.0f);
                if (!player.getAbilities().creativeMode) {
                    stack.decrement(1);
                }
            }
            if (removeDaub) {
                if (world.isClient) return ActionResult.SUCCESS;
                if (!player.getAbilities().creativeMode) {
                    Item newItem = switch (type) {
                        case CLAY -> ModItems.CLAY_DAUB_BALL.get();
                        case COARSE_CLAY -> ModItems.COARSE_CLAY_DAUB_BALL.get();
                        case MUD -> ModItems.MUD_DAUB_BALL.get();
                        case PACKED_MUD -> ModItems.PACKED_MUD_DAUB_BALL.get();
                        case SAND -> ModItems.SAND_DAUB_BALL.get();
                    };
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
                    stack.damage(1, player, playerEntity ->
                            playerEntity.sendToolBreakStatus(hand));
                }
                world.playSound(null, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 0.75f, 1.0f);
            }

            BlockState newState;
            if (newBlock instanceof WattleBlock) {
                newState = ((WattleBlock)newBlock).getPlacementState(world, pos);
            } else {
                newState = newBlock.getDefaultState();
            }
            world.setBlockState(pos, newState, 3);
            player.incrementStat(Stats.USED.getOrCreateStat(item));
            return ActionResult.SUCCESS;
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    public DaubLevel getLevel(Block block) {
        if (
            block == ModBlocks.SCATTERED_CLAY_DAUB.get() ||
            block == ModBlocks.SCATTERED_COARSE_CLAY_DAUB.get() ||
            block == ModBlocks.SCATTERED_MUD_DAUB.get() ||
            block == ModBlocks.SCATTERED_PACKED_MUD_DAUB.get() ||
            block == ModBlocks.SCATTERED_SAND_DAUB.get()
        ) {
            return DaubLevel.SCATTERED_DAUB;
        }
        if (
            block == ModBlocks.COVERED_CLAY_DAUB.get() ||
            block == ModBlocks.COVERED_COARSE_CLAY_DAUB.get() ||
            block == ModBlocks.COVERED_MUD_DAUB.get() ||
            block == ModBlocks.COVERED_PACKED_MUD_DAUB.get() ||
            block == ModBlocks.COVERED_SAND_DAUB.get()
        ) {
            return DaubLevel.COVERED_DAUB;
        }
        if (
            block == ModBlocks.CLAY_DAUB.get() ||
            block == ModBlocks.COARSE_CLAY_DAUB.get() ||
            block == ModBlocks.MUD_DAUB.get() ||
            block == ModBlocks.PACKED_MUD_DAUB.get() ||
            block == ModBlocks.SAND_DAUB.get()
        ) {
            return DaubLevel.DAUB;
        }
        return null;
    }

    public DaubType getType(Block block) {
        if (
            block == ModBlocks.SCATTERED_CLAY_DAUB.get() ||
            block == ModBlocks.COVERED_CLAY_DAUB.get() ||
            block == ModBlocks.CLAY_DAUB.get()
        ) {
            return DaubType.CLAY;
        }
        if (
            block == ModBlocks.SCATTERED_COARSE_CLAY_DAUB.get() ||
            block == ModBlocks.COVERED_COARSE_CLAY_DAUB.get() ||
            block == ModBlocks.COARSE_CLAY_DAUB.get()
        ) {
            return DaubType.COARSE_CLAY;
        }
        if (
            block == ModBlocks.SCATTERED_MUD_DAUB.get() ||
            block == ModBlocks.COVERED_MUD_DAUB.get() ||
            block == ModBlocks.MUD_DAUB.get()
        ) {
            return DaubType.MUD;
        }
        if (
            block == ModBlocks.SCATTERED_PACKED_MUD_DAUB.get() ||
            block == ModBlocks.COVERED_PACKED_MUD_DAUB.get() ||
            block == ModBlocks.PACKED_MUD_DAUB.get()
        ) {
            return DaubType.PACKED_MUD;
        }
        if (
            block == ModBlocks.SCATTERED_SAND_DAUB.get() ||
            block == ModBlocks.COVERED_SAND_DAUB.get() ||
            block == ModBlocks.SAND_DAUB.get()
        ) {
            return DaubType.SAND;
        }
        return null;
    }

    public DaubType getType(Item item) {
        if (item == ModItems.CLAY_DAUB_BALL.get()) {
            return DaubType.CLAY;
        }
        if (item == ModItems.COARSE_CLAY_DAUB_BALL.get()) {
            return DaubType.COARSE_CLAY;
        }
        if (item == ModItems.MUD_DAUB_BALL.get()) {
            return DaubType.MUD;
        }
        if (item == ModItems.PACKED_MUD_DAUB_BALL.get()) {
            return DaubType.PACKED_MUD;
        }
        if (item == ModItems.SAND_DAUB_BALL.get()) {
            return DaubType.SAND;
        }
        return null;
    }
}
