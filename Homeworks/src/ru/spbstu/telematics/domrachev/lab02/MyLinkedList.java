package ru.spbstu.telematics.domrachev.lab02;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Двусвязный список.
 */
public class MyLinkedList<T>
		implements ILinkedList, Iterable<T>
{
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
	}

	@Override
	public void addLast(Object o){
		INode<T> t = new INode<T>((T) o, tail.prev, tail);
		tail.prev.next = t;
		tail.prev = t;
	}

	@Override
	public Object getFirst()
			throws NoSuchElementException{
		if(head.next == tail)
			throw new NoSuchElementException();
		T t = head.next.item;
		return t;
	}

	@Override
	public Object getLast()
			throws NoSuchElementException{
		if(tail.prev == head)
			throw new NoSuchElementException();
		T t = tail.prev.item;
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
		return t;
	}

	@Override
	public Object removeLast()
		throws NoSuchElementException{
		if(tail.prev == head)
			throw new NoSuchElementException();
		T t = tail.prev.item;
		tail.prev.prev.next = tail;
		tail.prev = tail.prev.prev;
		return t;
	}
	
	private class MyIterator implements Iterator<T>{
		INode<T> current;
		
		public MyIterator(){
			current = head;
		}
		
		@Override
		public boolean hasNext() {
			boolean flag;
			if (flag = (current.next == tail)){
				current = head;
			}
			return !flag;
		}

		@Override
		public T next() {
			T t = current.next.item;
			current = current.next;
			return t;
		}

		@Override
		public void remove() {
			if (current != head && current != tail){
				current.prev.next = current.next;
				current.next.prev = current.prev;
			}
		}		
	}
	
	@Override
	public Iterator<T> iterator() {
		return new MyIterator();
	}
}
