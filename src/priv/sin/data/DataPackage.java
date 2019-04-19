package priv.sin.data;

public class DataPackage implements java.io.Serializable{
	private static final long serialVersionUID = 2L;
	public static final int PACKAGE_SIZE = 10;
	public Data[] datas = new Data[PACKAGE_SIZE];
	
	public DataPackage(int order, int sourcePid, int sourceSocketPort, String content)
	{
		for (int i = 0; i < PACKAGE_SIZE; i++)
		{
			datas[i] = new Data(order++, sourcePid, sourceSocketPort, content);
		}
	}
	
	public String toString()
	{
		return "{ DataPackage: " + datas[0].toString() + ", size = " + PACKAGE_SIZE + " }";
	}
}
