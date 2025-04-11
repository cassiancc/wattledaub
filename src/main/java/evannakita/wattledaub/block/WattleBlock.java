package evannakita.wattledaub.block;

import com.mojang.serialization.MapCodec;

import evannakita.wattledaub.ModBlocks;
import evannakita.wattledaub.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PaneBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class WattleBlock extends PaneBlock {

    public WattleBlock(Settings settings) {
        super(settings);
    }

	public BlockState getPlacementState(World world, BlockPos blockPos) {
		BlockPos blockPos2 = blockPos.north();
		BlockPos blockPos3 = blockPos.south();
		BlockPos blockPos4 = blockPos.west();
		BlockPos blockPos5 = blockPos.east();
		BlockState blockState = world.getBlockState(blockPos2);
		BlockState blockState2 = world.getBlockState(blockPos3);
		BlockState blockState3 = world.getBlockState(blockPos4);
		BlockState blockState4 = world.getBlockState(blockPos5);
		return this.getDefaultState()
			.with(NORTH, this.connectsTo(blockState, blockState.isSideSolidFullSquare(world, blockPos2, Direction.SOUTH)))
			.with(SOUTH, this.connectsTo(blockState2, blockState2.isSideSolidFullSquare(world, blockPos3, Direction.NORTH)))
			.with(WEST, this.connectsTo(blockState3, blockState3.isSideSolidFullSquare(world, blockPos4, Direction.EAST)))
			.with(EAST, this.connectsTo(blockState4, blockState4.isSideSolidFullSquare(world, blockPos5, Direction.WEST)));
	}

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);
        Block newBlock = getBlock(stack);
        if (newBlock != this) {
            if (!world.isClient) {
                world.setBlockState(pos, newBlock.getDefaultState(), Block.NOTIFY_ALL);
                if (!player.isCreative()) {
                    stack.decrement(1);
                }
                world.playSound(null, pos, SoundEvents.BLOCK_MUD_PLACE, SoundCategory.BLOCKS, 0.75f, 1.0f);
                player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
            }
            return ActionResult.success(world.isClient);
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    private @NotNull Block getBlock(ItemStack stack) {
        Block newBlock = this;
        if (stack.isOf(ModItems.CLAY_DAUB_BALL.get())) {
            newBlock = ModBlocks.SCATTERED_CLAY_DAUB.get();
        } else if (stack.isOf(ModItems.COARSE_CLAY_DAUB_BALL.get())) {
            newBlock = ModBlocks.SCATTERED_COARSE_CLAY_DAUB.get();
        } else if (stack.isOf(ModItems.MUD_DAUB_BALL.get())) {
            newBlock = ModBlocks.SCATTERED_MUD_DAUB.get();
        } else if (stack.isOf(ModItems.PACKED_MUD_DAUB_BALL.get())) {
            newBlock = ModBlocks.SCATTERED_PACKED_MUD_DAUB.get();
        } else if (stack.isOf(ModItems.SAND_DAUB_BALL.get())) {
            newBlock = ModBlocks.SCATTERED_SAND_DAUB.get();
        }
        return newBlock;
    }
}
