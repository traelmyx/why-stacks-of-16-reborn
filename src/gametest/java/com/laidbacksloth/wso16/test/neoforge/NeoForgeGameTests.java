package com.laidbacksloth.wso16.test.neoforge;

//? if neoforge {
import com.laidbacksloth.wso16.test.ClientSmoke;
import com.laidbacksloth.wso16.test.StackTests;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.gametest.GameTestHolder;
import net.neoforged.neoforge.gametest.PrefixGameTestTemplate;

@GameTestHolder(NeoForgeGameTests.MOD_ID)
@PrefixGameTestTemplate(false)
@Mod(NeoForgeGameTests.MOD_ID)
public class NeoForgeGameTests {
	public static final String MOD_ID = "wso16_gametest";

	public NeoForgeGameTests(Dist dist) {
		if (dist.isClient() && ClientSmoke.start()) {
			NeoForge.EVENT_BUS.addListener((ClientTickEvent.Post e) -> ClientSmoke.tick(net.minecraft.client.Minecraft.getInstance()));
		}
	}

	@GameTest(template = "empty")
	public static void sixteensBecomeSixtyFour(GameTestHelper helper) {
		StackTests.sixteensBecomeSixtyFour(helper);
	}

	@GameTest(template = "empty")
	public static void othersUntouched(GameTestHelper helper) {
		StackTests.othersUntouched(helper);
	}

	@GameTest(template = "empty")
	public static void pearlsFillOneSlot(GameTestHelper helper) {
		StackTests.pearlsFillOneSlot(helper);
	}
}
//?}
