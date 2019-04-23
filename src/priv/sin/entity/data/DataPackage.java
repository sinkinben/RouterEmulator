package priv.sin.entity.data;

public class DataPackage implements java.io.Serializable{
	private static final long serialVersionUID = 2L;
	public static final int PACKAGE_SIZE = 10;
	public Data[] datas = new Data[PACKAGE_SIZE];
	
	public DataPackage(int srcIP, int dstIP, int order, int srcPid, int srcSocketPort, String msg)
	{
		for (int i = 0; i < PACKAGE_SIZE; i++)
		{
			datas[i] = new Data(srcIP, dstIP, new MsgContent(order, srcPid, srcSocketPort, msg));
		}
	}
}
