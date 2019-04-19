package priv.sin.data;

public class Data implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private int order;
	private int sourcePid;
	private int sourceSocketPort;
	private String content;
	

	public Data(int order, int sourcePid, int sourceSocketPort, String content) {
		super();
		this.order = order;
		this.sourcePid = sourcePid;
		this.sourceSocketPort = sourceSocketPort;
		this.content = content;
	}
	
	public Data(Data data)
	{
		this(data.order, data.sourcePid, data.sourceSocketPort, data.content);
	}
	
	public String toString()
	{
		return "[ order=" + order + ": srcPid=" + sourcePid + ", srcSktPrt=" + sourceSocketPort + ", content='" + content + "' ]";
	}
	
}
