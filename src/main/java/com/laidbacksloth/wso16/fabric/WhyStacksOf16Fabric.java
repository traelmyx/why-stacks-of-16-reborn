package com.laidbacksloth.wso16.fabric;

//? if fabric {
/*import com.laidbacksloth.wso16.WhyStacksOf16;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.component.DataComponents;

public class WhyStacksOf16Fabric implements ModInitializer {
	@Override
	public void onInitialize() {
		WhyStacksOf16.loadConfig(FabricLoader.getInstance().getConfigDir());
		DefaultItemComponentEvents.MODIFY.register(context ->
			context.modify(WhyStacksOf16::shouldRaise, (builder, item) -> builder.set(DataComponents.MAX_STACK_SIZE, WhyStacksOf16.config.stackSize)));
	}
}
*///?}
