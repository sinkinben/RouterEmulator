package priv.sin.entity.router;

import priv.sin.entity.data.Data;
import priv.sin.entity.data.DataPackage;

public class RouterMemory {
	private int in = 0;
	private int out = 0;
	private static final int MEMORY_SIZE = 100;
	private Data[] memory = new Data[MEMORY_SIZE];
	
	public void copyDatas(DataPackage dataPackage)
	{
		for (int i = 0 ; i < dataPackage.PACKAGE_SIZE; i++)
		{
			memory[in] = new Data(dataPackage.datas[i].getSrcIP(), dataPackage.datas[i].getDstIP(), dataPackage.datas[i].getMsgContent());
			in = (in + 1) % MEMORY_SIZE;
		}
	}
	
	private boolean isFull()
	{
		return (in + 1) % MEMORY_SIZE == out ? true : false;
	}
}
