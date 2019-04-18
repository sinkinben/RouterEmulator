package priv.sin.test;

public class Data implements java.io.Serializable{
	String content;
	int src,dst;
	public Data(String content, int src, int dst) {
		super();
		this.content = content;
		this.src = src;
		this.dst = dst;
	}
	
	public String toString()
	{
		return (src+"->"+dst+": "+content);
	}
	
	
}
