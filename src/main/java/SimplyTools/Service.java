package SimplyTools;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

/**
 * This class used for call methods on client/server or on specific player
 * 
 * @author alesharik
 *
 */
public class Service {

	/**
	 * All registered methods
	 */
	private static ArrayList<Method> servicesMethods = new ArrayList<Method>();

	/**
	 * Add the class with methods who extends from this class which used in
	 * callMethod and other call methods in this class
	 * 
	 * @param serviceClass
	 *            class where contains methods
	 */
	public static void registerService(Class serviceClass) {
		Arrays.stream(serviceClass.getMethods()).forEach((method) -> servicesMethods.add(method));
	}

	/**
	 * Used for call registered here method on side which call this command
	 * 
	 * @param methodName
	 *            name of method, which need to call
	 * @param ctx
	 *            MessageContext from IMessageHandler
	 * @param data
	 *            NBTTagCompound which send to command which need to call
	 */
	public static void callMethod(String methodName, MessageContext ctx, NBTTagCompound data) {
		servicesMethods.stream().forEach((method) -> {
			if(method.getName().equals(methodName)) {
				try {
					method.invoke(null, ctx, data);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
				return;
			}
		});
	}

	/**
	 * Used for call method on specified side
	 * 
	 * @param methodName
	 *            name of method, which need to call
	 * @param data
	 *            NBTTagCompound which send to command which need to call
	 * @param side
	 *            Side, where need to call method
	 */

	public static void callMethodRemote(String methodName, NBTTagCompound data, Side side) {
		if(side.equals(Side.SERVER)) {
			SimplyTools.network.sendToServer(new CallMessage(methodName, data));
		} else {
			SimplyTools.network.sendToAll(new CallMessage(methodName, data));
		}
	}

	/**
	 * Used for call method on specific player
	 * 
	 * @param methodName
	 *            name of method, which need to call
	 * @param data
	 *            NBTTagCompound which send to command which need to call
	 * @param player
	 *            player where need to call method
	 */

	public static void callMethodOnSpecificClient(String methodName, NBTTagCompound data, EntityPlayerMP player) {
		SimplyTools.network.sendTo(new CallMessage(methodName, data), player);
	}

}
