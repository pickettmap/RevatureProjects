package com.intellijeep.util;



public class IntelliJeepHashMap<K, V> {

    private IntelliJeepArrayList<Node> buckets;
    int size;
    final int capacity;

    public IntelliJeepHashMap() {
        capacity = 16;
        buckets = new IntelliJeepArrayList<Node>(Node.class, 0);
        size = 0;
    }

    public IntelliJeepHashMap(int capacity) {
        this.capacity = capacity;
        buckets = new IntelliJeepArrayList<Node>(Node.class, 0);
        size = 0;
    }

    public void put(K key, V value) {
        int index = Math.abs(key.hashCode() % capacity);

        if(buckets.get(index) == null) {
            buckets.set(index, new Node(key, value));
        } else {
            Node temp;
            temp = buckets.get(index);

            if(buckets.get(index).key.equals(key)) {
                return;
            }

            while(temp.next != null) {
                if(temp.key.equals(key)) {
                    return;
                }

                temp = temp.next;
            }
            temp.next = new Node (key, value);
        }
        size++;
    }

    public void remove(K key) {
        int index = key.hashCode()%capacity;

        if(buckets.get(index) == null) {
            return;
        } else if(buckets.get(index).key.equals(key)) {
            buckets.set(index, buckets.get(index).next);
            size--;
        } else {
            Node temp;
            temp = buckets.get(index);

            while(temp.next != null && !temp.next.key.equals(key)) {
                temp = temp.next;
            }

            if(temp.next == null) {
                return;
            }

            if(temp.next.key.equals(key)) {
                System.out.println("Key Found");
                if(temp.next.next != null) {
                    temp = temp.next.next;
                } else {
                    temp.next = null;
                }
                size--;
            }
        }
    }

    public V get(K key) {
        int index = key.hashCode() % capacity;

        if(buckets.get(index) == null) {
            return null;
        } else {
            Node temp;
            temp = buckets.get(index);

            while(temp.key != key && temp.next != null) {
                temp = temp.next;
            }
            if(temp.key == key) {
                return (V) temp.value;
            }
        }
        return null;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for(Node n : buckets) {
            if(n != null) {
                Node n3 = n;
                do {
                    result.append(n3.toString()).append(System.lineSeparator());
                    n3 = n3.next;
                } while (n3 != null);
            }
        }
        return result.toString();
    }

    private class Node<K,V>{
        K key;
        V value;
        private Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public Node(K key, V value, Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Key: " + key.toString() + System.lineSeparator() +
                    "Value: " + value.toString();
        }
    }
}
