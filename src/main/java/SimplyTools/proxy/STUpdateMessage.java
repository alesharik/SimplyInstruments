package SimplyTools.proxy;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class STUpdateMessage implements IMessage {

	private int x;

	private int y;

	private int z;

	private NBTTagCompound data;

	public STUpdateMessage() {

	}

	public STUpdateMessage(int x, int y, int z, NBTTagCompound data) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.data = data;
	}

	public STUpdateMessage(NBTTagCompound data) {

		this.read(data);

	}

	private void read(NBTTagCompound data) {
		this.data = data.getCompoundTag("data");
		this.x = data.getInteger("x");
		this.y = data.getInteger("y");
		this.z = data.getInteger("z");
	}

	private NBTTagCompound write() {
		NBTTagCompound data = new NBTTagCompound();
		data.setTag("data", this.data);
		data.setInteger("x", this.x);
		data.setInteger("y", this.y);
		data.setInteger("z", this.z);
		return data;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.read(ByteBufUtils.readTag(buf));
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, this.write());
	}

	public static class Handler implements IMessageHandler<STUpdateMessage, IMessage> {
		@Override
		public IMessage onMessage(STUpdateMessage message, MessageContext ctx) {
			ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z).readFromNBT(message.data);
			ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z).markDirty();
			ctx.getServerHandler().playerEntity.worldObj.markBlockForUpdate(message.x, message.y, message.z);
			return null;
		}

	}

}
