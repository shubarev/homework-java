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
		t.removeFirst();
		t.removeFirst();
		t.getFirst();
		t.getFirst();
		t.removeLast();
		t.removeLast();
		t.getLast();
		t.getLast();
	}

}