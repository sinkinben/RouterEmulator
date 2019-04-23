package priv.sin.entity.router;

import priv.sin.entity.global.Global;

public class RoutingTable {
	private static final int TABLE_SIZE = 128;
	private int NR_TABLE = 0;
	private int[] netids = new int[TABLE_SIZE];
	private int[] nextAddrs = new int[TABLE_SIZE];
	
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
}
