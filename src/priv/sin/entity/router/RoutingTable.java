package priv.sin.entity.router;

import priv.sin.entity.global.Global;

public class RoutingTable {
	private static final int TABLE_SIZE = 128;
	private int NR_TABLE = 0;
	private int[] netids = new int[TABLE_SIZE];
	private int[] nextAddrs = new int[TABLE_SIZE];
	
	public RoutingTable()
	{
		initRoutingTable();
	}
	public void addItem(int netid, int nextAddr)
	{
		netids[NR_TABLE] = netid;
		nextAddrs[NR_TABLE] = nextAddr;
		NR_TABLE++;
	}
	
	public int getNextAddr(int ip)
	{
		for (int i = 0; i < NR_TABLE; i++)
		{
			if (netids[i] == (ip & Global.ipMask))
				return nextAddrs[i];
		}
		return 0;
	}

	public int getNR_TABLE() {
		return NR_TABLE;
	}

	public int[] getNetids() {
		return netids;
	}

	public int[] getNextAddrs() {
		return nextAddrs;
	}
	
	private void initRoutingTable()
	{
		int ipVal;
		for (int i = 0; i < Global.ipPool.length; i++)
		{
			ipVal = Global.string2ipv4(Global.ipPool[i]);
			addItem(ipVal, ipVal & Global.ipMask);
		}
	}
}
