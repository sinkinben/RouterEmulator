package priv.sin.entity.data;

public class MsgContent implements java.io.Serializable{
	private int order;
	private int srcPid;
	private int srcSocketPort;	//相当于路由器的端口
	private String msg;
	public MsgContent(int order, int srcPid, int srcSocketPort, String msg) {
		super();
		this.order = order;
		this.srcPid = srcPid;
		this.srcSocketPort = srcSocketPort;
		this.msg = msg;
	}
	public int getOrder() {
		return order;
	}
	public int getSrcPid() {
		return srcPid;
	}
	public int getSrcSocketPort() {
		return srcSocketPort;
	}
	public String getMsg() {
		return msg;
	}
	
	public String toString()
	{
		return "order="+order+", srcpid="+srcPid+", srcport="+srcSocketPort+", msg="+msg;
	}
	
	
	
	

}
