package com.laidbacksloth.wso16.test;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;

public final class StackTests {
	/** A vanilla item by id path; some (like banners) became colour collections in 26.x. */
	static Item item(String path) {
		return BuiltInRegistries.ITEM.stream().filter(i -> BuiltInRegistries.ITEM.getKey(i).getPath().equals(path)).findFirst().orElseThrow();
	}

	private StackTests() {
	}

	public static void sixteensBecomeSixtyFour(GameTestHelper helper) {
		for (Item item : List.of(Items.ENDER_PEARL, Items.EGG, Items.SNOWBALL, Items.OAK_SIGN, Items.CHERRY_HANGING_SIGN,
				Items.BUCKET, Items.ARMOR_STAND, item("white_banner"), Items.HONEY_BOTTLE, Items.WRITTEN_BOOK)) {
			helper.assertTrue(new ItemStack(item).getMaxStackSize() == 64, BuiltInRegistries.ITEM.getKey(item) + " stacks to " + new ItemStack(item).getMaxStackSize());
		}
		helper.succeed();
	}

	public static void othersUntouched(GameTestHelper helper) {
		helper.assertTrue(new ItemStack(Items.DIAMOND).getMaxStackSize() == 64, "diamond changed");
		helper.assertTrue(new ItemStack(Items.TOTEM_OF_UNDYING).getMaxStackSize() == 1, "totem changed");
		helper.assertTrue(new ItemStack(Items.WATER_BUCKET).getMaxStackSize() == 1, "water bucket changed");
		helper.assertTrue(new ItemStack(Items.DIAMOND_SWORD).getMaxStackSize() == 1, "sword changed");
		long left = BuiltInRegistries.ITEM.stream().filter(i -> new ItemStack(i).getMaxStackSize() == 16).count();
		helper.assertTrue(left == 0, left + " items still stack to 16");
		helper.succeed();
	}

	public static void pearlsFillOneSlot(GameTestHelper helper) {
		Player player = helper.makeMockPlayer(net.minecraft.world.level.GameType.SURVIVAL);
		player.getInventory().clearContent();
		player.getInventory().add(new ItemStack(Items.ENDER_PEARL, 40));
		player.getInventory().add(new ItemStack(Items.ENDER_PEARL, 24));
		helper.assertTrue(player.getInventory().getItem(0).getCount() == 64, "first slot holds " + player.getInventory().getItem(0).getCount());
		helper.assertTrue(player.getInventory().getItem(1).isEmpty(), "pearls spilled into a second slot");
		helper.succeed();
	}
}
