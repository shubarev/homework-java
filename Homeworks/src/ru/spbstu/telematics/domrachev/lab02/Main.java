package ru.spbstu.telematics.domrachev.lab02;

public class Main {

	public static void main(String[] args) {
		MyLinkedList<Integer>t = new MyLinkedList<Integer>();
		
		t.addFirst(4);
		t.addFirst(3);
		t.addFirst(2);
		t.addFirst(1);
		t.addLast(5);
		t.addLast(6);
		t.addLast(7);
		t.addLast(8);
		
		for (Integer o: t){
			System.out.print(o);
		}
		System.out.println();
		
		t.removeFirst();
		t.removeFirst();
		t.removeFirst();
		t.removeFirst();
		t.removeFirst();
		t.getFirst();
		t.getFirst();
		t.getLast();
		t.getLast();
		
		for (Integer o: t){
			System.out.print(o + "!");
		}
		System.out.println();
	}

}
