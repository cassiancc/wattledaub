package evannakita.wattledaub;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class BlockSupplier {
	private final String blockID;
	private final RegistryObject<Block> block;
	private final RegistryObject<BlockItem> item;

	public BlockSupplier(String blockID, RegistryObject<Block> block, RegistryObject<BlockItem> item) {
		this.blockID = blockID;
		this.block = block;
		this.item = item;
	}

	public RegistryObject<Block> getBlockSupplier() {
		return block;
	}

	public Block asBlock() {
		return block.get();
	}

	public Item asItem() {
		return item.get();
	}

	public RegistryObject<BlockItem> getItemSupplier() {
		return item;
	}

	public BlockState defaultBlockState() {
		return block.get().getDefaultState();
	}

	public String getID() {
		return blockID;
	}

	public Block get() {
		return block.get();
	}
}
