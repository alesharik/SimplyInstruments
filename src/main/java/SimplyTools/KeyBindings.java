package SimplyTools;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;

public class KeyBindings {

	public static KeyBinding selectModeSwitch;

	public static void init() {

		selectModeSwitch = new KeyBinding("key.selectModeSwitch", Keyboard.KEY_NUMPAD9, "key.categories.SimplyTools");

		ClientRegistry.registerKeyBinding(selectModeSwitch);
	}

}
