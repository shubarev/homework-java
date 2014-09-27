package ru.spbstu.telematics.domrachev.lab02;

/**
 * Двусвязный список.
 */
public class MyLinkedList<T> implements ILinkedList {
	int size = 0;
	private class INode<T>{
		T item;
		INode<T> prev;
		INode<T> next;
		
		INode(T item, INode<T> prev, INode<T> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
	}
	private INode<T> head;
	private INode<T> tail;
	
	public MyLinkedList(){
		head = new INode<T>(null, null, tail);
		tail = new INode<T>(null, head, null);
		head.next = tail;
		tail.prev = head;
	}
	
	@Override
	public void addFirst(Object o){
		INode<T> t = new INode<T>((T) o, head, head.next);
		head.next.prev = t;
		head.next = t;
		size++;
		
		INode<T> cur = head.next;
		while(cur != tail){
			System.out.print(cur.item);
			cur = cur.next;
		}
		System.out.println();
	}

	@Override
	public void addLast(Object o){
		INode<T> t = new INode<T>((T) o, tail.prev, tail);
		tail.prev.next = t;
		tail.prev = t;
		size++;
		
		INode<T> cur = head.next;
		while(cur != tail){
			System.out.print(cur.item);
			cur = cur.next;
		}
		System.out.println();
	}

	@Override
	public Object getFirst(){
		T t = head.next.item;
		INode<T> cur = head.next;
		while(cur != tail){
			System.out.print(cur.item);
			cur = cur.next;
		}
		System.out.println();
		return t;
	}

	@Override
	public Object getLast(){
		T t = tail.prev.item;
		INode<T> cur = head.next;
		while(cur != tail){
			System.out.print(cur.item);
			cur = cur.next;
		}
		System.out.println();
		return t;
	}

	@Override
	public Object removeFirst(){
		T t = head.next.item;
		head.next.next.prev = head;
		head.next = head.next.next;
		INode<T> cur = head.next;
		while(cur != tail){
			System.out.print(cur.item);
			cur = cur.next;
		}
		System.out.println();
		return t;
	}

	@Override
	public Object removeLast(){
		T t = tail.prev.item;
		tail.prev.prev.next = tail;
		tail.prev = tail.prev.prev;
		INode<T> cur = head.next;
		while(cur != tail){
			System.out.print(cur.item);
			cur = cur.next;
		}
		System.out.println();
		return t;
	}
}