package com.laidbacksloth.wso16;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class WhyStacksOf16 {
	public static final String MOD_ID = "wso16";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	public static Config config = new Config();

	private WhyStacksOf16() {
	}

	public static final class Config {
		/** What items that normally stack to 16 stack to instead. Vanilla allows up to 99. */
		public int stackSize = 64;
		/** Item ids to leave at 16, e.g. "minecraft:bucket". */
		public List<String> exclude = new ArrayList<>();
	}

	public static void loadConfig(Path configDir) {
		Path file = configDir.resolve(MOD_ID + ".json");
		try {
			if (Files.exists(file)) {
				Config read = GSON.fromJson(Files.readString(file), Config.class);
				if (read != null) {
					if (read.exclude == null) read.exclude = new ArrayList<>();
					read.stackSize = Math.max(1, Math.min(99, read.stackSize));
					config = read;
				}
			} else {
				Files.createDirectories(configDir);
				Files.writeString(file, GSON.toJson(config));
			}
		} catch (IOException | RuntimeException e) {
			LOGGER.error("Could not read {}, using defaults", file, e);
		}
	}

	/** Whether this item's default stack size should be raised. */
	public static boolean shouldRaise(Item item) {
		return item.components().getOrDefault(DataComponents.MAX_STACK_SIZE, 1) == 16
			&& !config.exclude.contains(BuiltInRegistries.ITEM.getKey(item).toString());
	}
}
