package com.laidbacksloth.wso16.test;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.level.GameType;

import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.nio.file.Files;

// Run with -Dsmoketest=true: creates a flat world, takes a screenshot and quits. Exit code 1 on failure.
public final class ClientSmoke {

    public static final boolean ENABLED = Boolean.getBoolean("smoketest");
    private static final long TIMEOUT_MS = 5 * 60 * 1000;

    private static int stage;
    private static int ticks;

    private ClientSmoke() {
    }

    public static boolean start() {
        if (!ENABLED) {
            return false;
        }
        Thread watchdog = new Thread(() -> {
            try {
                Thread.sleep(TIMEOUT_MS);
                fail("timed out");
            } catch (InterruptedException ignored) {
            }
        }, "smoketest-watchdog");
        watchdog.setDaemon(true);
        watchdog.start();
        return true;
    }

    public static void tick(Minecraft mc) {
        try {
            switch (stage) {
                case 0 -> {
                    Screen screen = SmokeCompat.screen(mc);
                    if (screen != null && SmokeCompat.overlayHidden(mc) && ++ticks >= 20) {
                        if (screen.getClass().getSimpleName().equals("LoadingErrorScreen")) {
                            fail("mod loading errors");
                        }
                        ticks = 0;
                        mc.options.tutorialStep = net.minecraft.client.tutorial.TutorialSteps.NONE;
                        SmokeCompat.createFlatWorld(mc, GameType.SURVIVAL);
                        stage = 1;
                    }
                }
                case 1 -> {
                    if (mc.level != null && mc.player != null && SmokeCompat.screen(mc) == null) {
                        stage = 2;
                    }
                }
                case 2 -> {
                    ticks++;
                    if (ticks == 40) {
                        setup(mc);
                    }
                    if (ticks == 70) {
                        SmokeCompat.setScreen(mc, new InventoryScreen(mc.player));
                    }
                    if (ticks == 110) {
                        SmokeCompat.screenshot(mc, "inventory.png");
                        SmokeCompat.setScreen(mc, null);
                    }
                    if (ticks >= 130) {
                        ItemStack pearls = mc.player.getInventory().getItem(0);
                        if (!pearls.is(Items.ENDER_PEARL) || pearls.getCount() != 64 || pearls.getMaxStackSize() != 64) {
                            fail("client sees " + pearls + " max " + pearls.getMaxStackSize());
                        }
                        Files.writeString(mc.gameDirectory.toPath().resolve("smoke-ok"), "ok\n");
                        stage = 3;
                        mc.stop();
                    }
                }
                default -> {
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
            fail(t.toString());
        }
    }

    // a hotbar full of things that used to stack to 16, all at 64
    private static void setup(Minecraft mc) {
        var server = mc.getSingleplayerServer();
        server.execute(() -> {
            var player = server.getPlayerList().getPlayers().get(0);
            var items = new net.minecraft.world.item.Item[] {Items.ENDER_PEARL, Items.SNOWBALL, Items.EGG, Items.OAK_SIGN, Items.BUCKET,
                Items.ARMOR_STAND, Items.RED_BANNER, Items.HONEY_BOTTLE, Items.SPRUCE_HANGING_SIGN};
            for (int i = 0; i < items.length; i++) {
                player.getInventory().setItem(i, new ItemStack(items[i], 64));
            }
            player.getInventory().setItem(9, new ItemStack(Items.ENDER_PEARL, 64));
            player.getInventory().setItem(10, new ItemStack(Items.ENDER_PEARL, 64));
            player.inventoryMenu.broadcastChanges();
        });
    }

    public static void fail(String why) {
        System.err.println("Smoke test failed: " + why);
        System.out.println("[clientsmoke] FAILED: " + why);
        System.err.flush();
        Runtime.getRuntime().halt(1);
    }
}
