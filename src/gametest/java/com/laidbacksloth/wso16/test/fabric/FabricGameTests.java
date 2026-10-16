package com.laidbacksloth.wso16.test.fabric;

//? if fabric {
/*import com.laidbacksloth.wso16.test.StackTests;
import net.fabricmc.fabric.api.gametest.v1.FabricGameTest;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;

public class FabricGameTests implements FabricGameTest {
	private static final String EMPTY = "wso16_gametest:empty";

	@GameTest(template = EMPTY)
	public void sixteensBecomeSixtyFour(GameTestHelper helper) {
		StackTests.sixteensBecomeSixtyFour(helper);
	}

	@GameTest(template = EMPTY)
	public void othersUntouched(GameTestHelper helper) {
		StackTests.othersUntouched(helper);
	}

	@GameTest(template = EMPTY)
	public void pearlsFillOneSlot(GameTestHelper helper) {
		StackTests.pearlsFillOneSlot(helper);
	}
}
*///?}
