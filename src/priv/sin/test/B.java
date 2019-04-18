package priv.sin.test;

import java.util.ArrayList;

public class B {
	private ArrayList<String> list;
	public B(ArrayList<String> list)
	{
		this.list = list;
		this.list.add("haha");
		System.out.println("B: " + list.toString());
	}

}
