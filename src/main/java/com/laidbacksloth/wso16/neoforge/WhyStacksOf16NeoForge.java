package com.laidbacksloth.wso16.neoforge;

//? if neoforge {
import com.laidbacksloth.wso16.WhyStacksOf16;
import net.minecraft.core.component.DataComponents;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;

@Mod(WhyStacksOf16.MOD_ID)
public class WhyStacksOf16NeoForge {
	public WhyStacksOf16NeoForge(IEventBus modBus) {
		WhyStacksOf16.loadConfig(FMLPaths.CONFIGDIR.get());
		modBus.addListener((ModifyDefaultComponentsEvent event) ->
			event.modifyMatching(WhyStacksOf16::shouldRaise, builder -> builder.set(DataComponents.MAX_STACK_SIZE, WhyStacksOf16.config.stackSize)));
	}
}
//?}
