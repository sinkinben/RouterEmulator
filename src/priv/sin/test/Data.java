package priv.sin.test;

public class Data implements java.io.Serializable{
	String content;
	int order;
	int srcpid;
	public Data(String content, int order, int srcpid) {
		super();
		this.content = content;
		this.order = order;
		this.srcpid = srcpid;
	}
	
	public String toString()
	{
		return "["+order+" "+content+" from "+ srcpid +"]";
	}
	
}
