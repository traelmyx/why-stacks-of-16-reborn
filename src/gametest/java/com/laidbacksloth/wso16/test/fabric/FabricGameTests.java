package com.laidbacksloth.wso16.test.fabric;

//? if fabric {
/*import com.laidbacksloth.wso16.test.StackTests;
import net.minecraft.gametest.framework.GameTestHelper;
//? if >=1.21.5 {
import net.fabricmc.fabric.api.gametest.v1.GameTest;
//?} else {
/^import net.fabricmc.fabric.api.gametest.v1.FabricGameTest;
import net.minecraft.gametest.framework.GameTest;
^///?}

//? if >=1.21.5 {
public class FabricGameTests {
	private static final String EMPTY = "wso16_gametest:empty";

	@GameTest(structure = EMPTY, maxTicks = 100)
	public void sixteensBecomeSixtyFour(GameTestHelper helper) {
		StackTests.sixteensBecomeSixtyFour(helper);
	}

	@GameTest(structure = EMPTY, maxTicks = 100)
	public void othersUntouched(GameTestHelper helper) {
		StackTests.othersUntouched(helper);
	}

	@GameTest(structure = EMPTY, maxTicks = 100)
	public void pearlsFillOneSlot(GameTestHelper helper) {
		StackTests.pearlsFillOneSlot(helper);
	}
}
//?} else {
/^public class FabricGameTests implements FabricGameTest {
	private static final String EMPTY = "wso16_gametest:empty";

	@GameTest(template = EMPTY, timeoutTicks = 100)
	public void sixteensBecomeSixtyFour(GameTestHelper helper) {
		StackTests.sixteensBecomeSixtyFour(helper);
	}

	@GameTest(template = EMPTY, timeoutTicks = 100)
	public void othersUntouched(GameTestHelper helper) {
		StackTests.othersUntouched(helper);
	}

	@GameTest(template = EMPTY, timeoutTicks = 100)
	public void pearlsFillOneSlot(GameTestHelper helper) {
		StackTests.pearlsFillOneSlot(helper);
	}
}
^///?}
*///?}
