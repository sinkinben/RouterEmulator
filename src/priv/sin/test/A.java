package priv.sin.test;

import java.util.ArrayList;

public class A {
	public static void main(String[] args)
	{
		ArrayList<String> list = new ArrayList<>();
		
		B b = new B(list);
		System.out.println("A: " + list);
	}
}
