import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T>{
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public boolean add(T value){
        Node<T> node = new Node<>(value);
        if(head == null){
            head = node;
            tail = node;
        }else{
            tail.next = node;
            tail = node;
        }
        size++;
        return true;
    }

    public T get(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException(index);
        }
        Node<T> curr = head;
        while (index > 0){
            curr = curr.next;
            index--;
        }
        return curr.value;
    }

    public void remove(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException(index);
        }
        Node<T> curr = head;
        if(index == 0){
            head = curr.next;
            size--;
            return;
        }
        while (index > 1){
            curr = curr.next;
            index--;
        }
        curr.next = curr.next.next;

        if(curr.next == null){
            tail = curr;
        }
        size--;
    }
    public int size(){
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator<>(head);
    }

    private static class Node<T>{
        private T value;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }
    }

    private static class MyIterator<T> implements Iterator<T>{
        private Node<T> curr;
        private MyIterator(Node<T> head){
            curr = head;
        }
        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public T next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            T res = curr.value;
            curr = curr.next;
            return res;
        }
    }
}
