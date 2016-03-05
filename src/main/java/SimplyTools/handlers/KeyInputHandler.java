package SimplyTools.handlers;

import SimplyTools.KeyBindings;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

public class KeyInputHandler {

	public static int selectModeSwitch = 0;

	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event) {
		if(KeyBindings.selectModeSwitch.isPressed()) {
			if(selectModeSwitch > 1 || selectModeSwitch < 0) {
				selectModeSwitch = 0;
			} else {
				selectModeSwitch++;
			}
		}
	}

}
