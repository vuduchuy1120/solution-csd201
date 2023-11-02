package linkedlist;

public class Node {

    Person info;
    Node next;

    public Node() {
    }

    public Node(Person info, Node next) {
        this.info = info;
        this.next = next;
    }
    
    public Node(Person info) {
        this.info = info;
        this.next = null;
    }
    
    Node(String name, int age) {
        info = new Person(name, age);
        next = null;
    }

    @Override
    public String toString() {
        return "{" + info + '}';
    }

}
