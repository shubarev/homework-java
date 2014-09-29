package ru.spbstu.telematics.domrachev.lab02;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Двусвязный список.
 */
public class MyLinkedList<T>
		implements Iterable<T>, ILinkedList
{
	int size = 0;
	private class INode<T> implements Iterator<T>{
		T item;
		INode<T> prev;
		INode<T> next;
		
		INode(T item, INode<T> prev, INode<T> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			return null;
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
		
		printOut();
	}

	@Override
	public void addLast(Object o){
		INode<T> t = new INode<T>((T) o, tail.prev, tail);
		tail.prev.next = t;
		tail.prev = t;
		size++;
		
		printOut();
	}

	@Override
	public Object getFirst(){
		T t = head.next.item;
		printOut();
		return t;
	}

	@Override
	public Object getLast(){
		T t = tail.prev.item;
		printOut();
		return t;
	}

	@Override
	public Object removeFirst()
		throws NoSuchElementException
	{
		if(head.next == tail)
			throw new NoSuchElementException();
		T t = head.next.item;
		head.next.next.prev = head;
		head.next = head.next.next;
		printOut();
		return t;
	}
	
	public void printOut(){
		INode<T> cur = head.next;
		while(cur != tail){
			System.out.print(cur.item);
			cur = cur.next;
		}
		System.out.println();
	}

	@Override
	public Object removeLast()
		throws NoSuchElementException{
		if(tail.prev == head)
			throw new NoSuchElementException();
		T t = tail.prev.item;
		tail.prev.prev.next = tail;
		tail.prev = tail.prev.prev;
		printOut();
		return t;
	}

	@Override
	public Iterator<T> iterator() {
		return null;
	}
}
