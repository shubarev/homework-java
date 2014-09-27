package ru.spbstu.telematics.domrachev.lab02;

/**
 * Двусвязный список.
 */
public interface ILinkedList {
    /**
     * Добавить элемент в голову списка
     */
    void addFirst(Object o);

    /**
     * Добавить элемент в хвост списка
     * @param o
     */
    void addLast(Object o);

    /**
     * Взять первый элемент. Но не удалять из списка!
     * @return
     */
    Object getFirst();

    /**
     * Взять последний элемент. Но не удалять!
     * @return
     */
    Object getLast();

    /**
     * Вернуть первый элемент и удалить его из списка.
     * @return
     */
    Object removeFirst();

    /**
     * Вернуть последний элемент и удалить его из списка.
     * @return
     */
    Object removeLast();

}
