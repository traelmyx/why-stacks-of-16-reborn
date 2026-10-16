package com.laidbacksloth.wso16.test.fabric;

//? if fabric {
/*import com.laidbacksloth.wso16.test.ClientSmoke;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class FabricClientSmoke implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		if (ClientSmoke.start()) ClientTickEvents.END_CLIENT_TICK.register(ClientSmoke::tick);
	}
}
*///?}
