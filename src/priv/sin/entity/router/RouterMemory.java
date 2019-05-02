package priv.sin.entity.router;

import priv.sin.entity.data.Data;
import priv.sin.entity.data.DataPackage;

public class RouterMemory {
	private static int in = 0;
	private static int out = 0;

	public static final int MEMORY_SIZE = 32 * 32;
	private Data[] memory = new Data[MEMORY_SIZE];

	public void copyDatas(DataPackage dataPackage) {
		for (int i = 0; i < dataPackage.PACKAGE_SIZE; i++) {
			memory[in] = new Data(dataPackage.datas[i].getSrcIP(), dataPackage.datas[i].getDstIP(),
					dataPackage.datas[i].getMsgContent());
			in = (in + 1) % MEMORY_SIZE;
		}
	}

	public void getDatas(int size) {
		out = (out + size) % MEMORY_SIZE;
	}

	public boolean isFull() {
		return (in + 1) % MEMORY_SIZE == out;
	}

	public boolean isEmpty() {
		return (in == out);
	}

	public Data getData() {
		Data data = memory[out];
		out = (out + 1) % MEMORY_SIZE;
		return data;
	}

	public static int getIn() {
		return in;
	}

	public static int getOut() {
		return out;
	}

	public static int getMemorySize() {
		return MEMORY_SIZE;
	}

	public Data[] getMemory() {
		return memory;
	}

}
