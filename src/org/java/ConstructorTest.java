package org.java;

public class ConstructorTest {
static int key =2;
	public ConstructorTest() {
		System.out.println("test");
	}
	public static void main(String[] args) {
		//System.out.println("test1");
		switch (key) {
		case 1:
			System.out.println("first case");
		case 2:
			System.out.println("second case");
			break;
		default:
			break;
		}
	}

}
