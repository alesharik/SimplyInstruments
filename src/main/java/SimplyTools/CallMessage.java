package SimplyTools;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/**
 * This message class used for call methods
 * 
 * @author alesharik
 * @see Service
 */
public class CallMessage implements IMessage {

	private String methodName;
	private NBTTagCompound data;

	/**
	 * Empty constructor. Used for technical goals
	 */
	public CallMessage() {
	}

	/**
	 * This constructor used to create new CallMessage with specific name of
	 * method and data
	 * 
	 * @param methodName
	 *            name of method
	 * @param data
	 *            NBTTagCompound custom data
	 */
	public CallMessage(String methodName, NBTTagCompound data) {
		this.methodName = methodName;
		this.data = data;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		NBTTagCompound messageData = ByteBufUtils.readTag(buf);
		this.methodName = messageData.getString("methodName");
		this.data = messageData.getCompoundTag("data");
	}

	@Override
	public void toBytes(ByteBuf buf) {
		NBTTagCompound messageData = new NBTTagCompound();
		messageData.setString("methodName", this.methodName);
		messageData.setTag("data", this.data);
		ByteBufUtils.writeTag(buf, messageData);
	}

	/**
	 * IMessageHandler for this message
	 * 
	 * @author alesharik
	 *
	 */
	public static class Handler implements IMessageHandler<CallMessage, IMessage> {
		@Override
		public IMessage onMessage(CallMessage message, MessageContext ctx) {
			Service.callMethod(message.methodName, ctx, message.data);
			return null;
		}
	}

}
