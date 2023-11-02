/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

/**
 *
 * @author ASUS
 */
public class MyList {

    Node head;
    Node tail;

    // (1)
    MyList() {
        head = null;
        tail = null;
    }

    // (2) check linked rỗng.
    boolean isEmpty() {
        return head == null;
    }

    // (3)  xoá linked list
    void clear() {
        head = null;
        tail = null;
    }

    // (4) add vào cuối.
    void addLast(Person x) {
        Node p = new Node(x);
        if (isEmpty()) {
            tail = head = p;
            return;
        } else {
            tail.next = p;
            tail = p;
        }
    }

    // (5) in ra thông tin của 1 node
    void visit(Node p) {
        if (p != null) {
            System.out.print(p.info + " ");
        }
    }

//    void traverse() {
//        Node p = head;
//        while (p != null) {
//            System.out.print(p.info + " ");
//            if (p.next != null) {
//                System.out.print(" --> ");
//            }
//            p = p.next;
//        }
//        System.out.println();
//    }
    // (6) duyệt tất cả các node.
    void traverse() {
        Node p = head;
        while (p != null) {
            visit(p);
            p = p.next;
        }
        System.out.println();
    }

    // array a biểu diễn tên, array b biểu diễn tuổi.
    void addLastMany(String[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            addLast(new Person(a[i], b[i]));
        }
    }

    // (8) 
    Node searchByName(String xName) {
        Node p = head;
        while (p != null) {
            if (p.info.name.equals(xName)) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    Node searchByAge(int xAge) {
        Node p = head;
        while (p != null) {
            if (p.info.age == xAge) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    // (9) 
    void addFirst(Person x) {
//        Node p = new Node(x);
//        if (isEmpty()) {
//            head = p;
//            tail = p;
//        } else {
//            p.next = head;
//            head = p;
//        }
        head = new Node(x, head);
        if (tail == null) {
            tail = head;
        }
    }

    // (10)
    void addAfter(Node p, Person x) {
        if (p == null) {
            return;
        }
        Node pNext = p.next;
        Node q = new Node(x, pNext);
        p.next = q;
        if (tail == p) {
            tail = q;
        }
    }

    // (11)
    void addBefore(Node q, Person x) {
        if (q == null) {
            return;
        }
        if (q == head) { // chèn trước node head
            addFirst(x);
            return;
        }
        // tìm node f trước node q
        Node f = head;
        while (f != null && f.next != q) {
            f = f.next;
        }
        if (f == null) // q is not in the list
        {
            return;
        }
        addAfter(f, x);
    }
    
    
    void removeFirst() {
        if (isEmpty()) {
            return;
        }
        head = head.next;
        if (head == null) {
            tail = null;
        }
    }

    // (12) xoá đi 1 node trong linked list.
    void remove(Node q) {
        if (q == null) {
            return;
        }
        if (q == head) {
            removeFirst();
            return;
        }
        Node f = head;
        while (f != null && f.next != q) {
            f = f.next;
        }
        if (f == null) // q is not in the list
        {
            return;
        }
        // xoa q khoi list
        f.next = q.next;
        if (f.next == null) { // q là tail
            tail = f;
        }
    }

    // (13) 
    void remove(String xname) {
        Node p = searchByName(xname);
        remove(p);
    }

    // (14)
    void remove(int xAge) {
        Node p = searchByAge(xAge);
        remove(p);
    }

    // (15) xoá tất cả những thằng có tuổi truyền vào.
    void removeAll(int xAge) { // String name;
        Node p;
        while (true) {
            p = searchByAge(xAge);
            if (p == null) {
                break;
            }
            remove(p);
        }
    }

    // (16) 
    Node pos(int k) {
        int count = 0;
        Node p = head;
        while (p != null) {
            if (count == k) {
                return p;
            }
            count++;
            p = p.next;
        }
        return null;
    }

    // (17)
    void removePos(int k) {
        Node p = pos(k);
        remove(p);
    }

    // (18)
    void sortByName() {
        Node pi, pj; // int i, j
        Person temp;  // int tmp
        pi = head;  // i = 0;
        while (pi != null) {
            pj = pi.next; // j = i + 1;
            while (pj != null) {
                if (pi.info.name.compareTo(pj.info.name) > 0) {
                    temp = pi.info;
                    pi.info = pj.info;
                    pj.info = temp;
                }
                pj = pj.next; // j++;
            }
            pi = pi.next; // i++;
        }
    }

    // (19) 
    void sortByAge() {
        Node pi, pj;
        Person temp;
        pi = head;
        while (pi != null) {
            pj = pi.next;
            while (pj != null) {
                if (pi.info.age > pj.info.age) {
                    temp = pi.info;
                    pi.info = pj.info;
                    pj.info = temp;
                }
                pj = pj.next;
            }
            pi = pi.next;
        }
    }

    // (20) // đếm xem linked list có bao nhiêu node.
    int size() {
        int count = 0;
        Node p = head;
        while (p != null) {
            count++;
            p = p.next;
        }
        return (count);
    }

    // (21)
    Person[] toArray() {
        int n, i;
        n = size(); // kích thước linked list
        Person[] persons = new Person[n];
        Node p = head; 
        i = 0;
        while (p != null) {
            persons[i++] = new Person(p.info.name, p.info.age);
            p = p.next;
        }
        return (persons);
    }

    // (22) đảo ngược linked list.
    void reverse() {
        MyList t = new MyList();
        Node p = head;
        while (p != null) {
            t.addFirst(p.info);
            p = p.next;
        }
        head = t.head;
        tail = t.tail;
    }

    // (22) 
    Node findMaxAge() {
        Node maxAge = head;  // i
        Node p = head.next; // i +1 
        while (p != null) {
            if (p.info.age > maxAge.info.age) {
                maxAge = p;
            }
            p = p.next;
        }
        return maxAge;
    }
 
    // (23)
    Node findMinAge() {
        Node minAge = head;
        Node p = head.next;
        while (p != null) {
            if (p.info.age < minAge.info.age) {
                minAge = p;
            }
            p = p.next;
        }
        return minAge;
    }

    // (24)
    void setData(Node p, Person x) {
        if (p != null) {
            p.info = x;
        }
    }
    
    // (25)
    void sortByAge(int k, int h) {
        if (k > h) {
            return;
        }
        if(k < 0){
            k = 0;
        }
        if(h > size() -1){
            h = size() - 1;
        }
        Node start, end, pi, pj;
        Person temp;
        start = pos(k); 
        end = pos(h + 1);
        pi = start; 
        while (pi != end) {
            pj = pi.next;
            while (pj != end) {
                if (pi.info.age > pj.info.age) {
                    temp = pi.info;
                    pi.info = pj.info;
                    pj.info = temp;
                }
                pj = pj.next;
            }
            pi = pi.next;
        }
    }
    
    // (26)
    void reverse(int k, int h){
        if(k >= h){
            return;
        }
        if (k < 0){
            return;
        }
        int n = size();
        if( h > n-1){
            return;
        }
        Person[] persons = toArray();
        int i, j;
        Person tmp;
        i = k;
        j = h;
        while(i < j){
            tmp = persons[i];
            persons[i] = persons[j];
            persons[j] = tmp;
            i++;
            j--;
        }
        clear();
        for (int l = 0; l < persons.length; l++) {
            addLast(persons[l]);
        }
    }

//    void readFromFlie(String filename) {
//        //đọc từng dòng của file
//        // format từng dòng:
//        //A | 2
//        //B | 2
//        //tạo đối tượng
//        //add vào linked list
//
//    }
//
//    void writeToFile(String filenam) {
//        // viết từng đối tượng vào file
//    }
//
//    //3
//    Node searchNode(Person x) {
//        Node p = head;
//        while (p != null) {
//            if (p.info == x) {
//                return p;
//            }
//            p = p.next;
//        }
//        return null;
//    }
//
//    Node search(String xname) {
//        Node p = head;
//        while (p != null) {
//            if (p.info.name.equalsIgnoreCase(xname)) {
//                return p;
//            }
//            p = p.next;
//        }
//        return null;
//    }
//
//   
//
//    //4
//    //5
//    Person deleteFromHead() {
//        Node tmp = null;
//        if (head == tail) {
//            tmp = head;
//            clear();
//        } else if (head != null) {
//            tmp = head;
//            head = head.next;
//        }
//        return tmp.info;
//    }
//
//    //6
//    Person deleteFromTail() {
//        Node tmp = null;
//        if (head == tail) {
//            tmp = tail;
//            clear();
//        } else {
//            tmp = head;
//            while (tmp.next != tail) {
//                tmp = tmp.next;
//            }
//            tail = tmp;
//            tmp = tmp.next;
//            tail.next = null;
//        }
//        return tmp.info;
//    }
//
//    //7
//    Person deleteAter(Node p) {
//        Node del = null;
//
//        if (head == tail) {
//            return del.info;
//        } else {
//            del = p.next;
//            p.next = del.next;
//            del.next = null;
//            if (p.next == null) {
//                tail = p;
//            }
//
//        }
//        return del.info;
//    }
//
//    //8
//    void dele(Person x) {
//        Node p = searchNode(x);
//        Node i = head;
//        if (p == null) {
//            return;
//        } else if (p == head) {
//            deleteFromHead();
//        } else if (p == tail) {
//            deleteFromTail();
//        } else {
//            while (i.next != p) {
//                i = i.next;
//            }
//            i.next = p.next;
//            p.next = null;
//        }
//    }
//
//    //10
//    int count() {
//        Node p = head;
//        int count = 0;
//        while (p != null) {
//            count++;
//            p = p.next;
//        }
//        return count;
//    }
//
//    //11 delete an i-th node on the list
//    void delete(int i) {
//        int count = i;
//        Node tmp = head;
//        Node p = head;
//        if (head == null) {
//            return;
//        } else if (head == tail) {
//            if (i == 1) {
//                deleteFromHead();
//            } else {
//                return;
//            }
//        } else {
//            p = p.next;
//            for (int j = 2; j <= i; j++) {
//                tmp.next = p;
//                p = p.next;
//            }
//            tmp.next = p.next;
//            p.next = null;
//        }
//    }
//    int max(){
//        Node current = head.next;
//        int max = head.info;
//        while(current != null){
//            if(max > current.info)
//                current = current.next;
//            else{
//                max = current.info;
//                current = current.next;
//            }
//        }
//        return max;
//    }
//   
//    
//    int sum(){
//        Node current = head;
//        int sum = 0;
//        while(current != null){
//            sum += current.info;
//            current = current.next;
//        }
//        return sum;
//    }
}
