package priv.sin.entity.data;

import priv.sin.entity.global.Global;

public class Data implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private int srcIP;
	private int dstIP;
	private MsgContent msgContent;
	public Data(int srcIP, int dstIP, MsgContent msgContent) {
		super();
		this.srcIP = srcIP;
		this.dstIP = dstIP;
		this.msgContent = msgContent;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getSrcIP() {
		return srcIP;
	}
	public int getDstIP() {
		return dstIP;
	}
	public MsgContent getMsgContent() {
		return msgContent;
	}
	
	public String toString()
	{
		String src = Global.ipv4String(srcIP);
		String dst = Global.ipv4String(dstIP);
		return "src=" + src + ", dst=" + dst + ", " + msgContent.toString();
	}
	
	
	
	
	
}
