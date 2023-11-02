/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class BSTree {

    Node root;

    BSTree() {
        root = null;
    }

    void clear() {
        root = null;
    }

    boolean isEmpty() {
        return root == null;
    }

    // (1)
    Node search(Node p, int x) {
        if (p == null) {
            return null;
        }
        if (p.info == x) {
            return p;
        }
        if (x < p.info) {
            return search(p.left, x);
        } else {
            return search(p.right, x);
        }
    }

    // (2)
    Node insert(Node p, int x) {
        if (p == null) {
            p = new Node(x);
            return p;
        }
        if (p.info == x) {
            System.out.println("The key already exists, no insertion");
            return p;
        }
        if (x < p.info) {
            p.left = insert(p.left, x);
        } else {
            p.right = insert(p.right, x);
        }
        return p;
    }

    // (3)
    void insert(int x) {
        root = insert(root, x);
    }

    // (4) insert không sử dụng đệ quy.
    void insert2(int x) {
        if (isEmpty()) {
            root = new Node(x);
            return;
        }
        Node f, p;
        f = null;
        p = root;
        while (p != null) {
            if (p.info == x) {
                System.out.println("Exists!");
                return;
            }
            f = p;
            if (x < p.info) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (x < f.info) {
            f.left = new Node(x);
        } else {
            f.right = new Node(x);
        }
    }

    // (5)
    void insertMany(int[] a) {
        for (int i = 0; i < a.length; i++) {
            insert2(a[i]);
        }
    }

    // (6)
    void visit(Node p) {
        if (p != null) {
            System.out.print(p.info + " ");
        }
    }

    // (7) root-left-right
    void preOrder(Node p) {
        if (p == null) {
            return;
        }
        visit(p);
        preOrder(p.left);
        preOrder(p.right);
    }

    // (8) left root right
    void inOrder(Node p) {
        if (p == null) {
            return;
        }
        inOrder(p.left);
        visit(p);
        inOrder(p.right);
    }

    // (9) left right root
    void postOrder(Node p) {
        if (p == null) {
            return;
        }
        postOrder(p.left);
        postOrder(p.right);
        visit(p);
    }

    // (10)
    void breadth(Node p) {
        if (p == null) {
            return;
        }
        MyQueue q = new MyQueue();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            visit(r);
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
    }

    // (11)
    void deleByCoppy(int x) {
        if (isEmpty()) {
            return;
        }
        Node f, p;
        f = null;
        p = root;
        while (p != null) {
            if (p.info == x) {
                break;
            }
            f = p;
            if (x < p.info) {
                p = p.left;
            } else {
                p = p.right;
            }
        }

        if (p == null) {
            return;
        }
        // tại đây p = nút cần xoá.
        // f là cha của p/

        // TH1: p là node lá
        if (p.left == null && p.right == null) {
            if (f == null) { // p là root
                root = null;
            } else {
                if (p == f.left) {
                    f.left = null;
                } else {
                    f.right = null;
                }
            }
            return;
        }

        //TH2: p có 1 con trái
        if (p.left != null && p.right == null) {
            if (f == null) { // p là root
                root = p.left;
            } else {
                if (p == f.left) {
                    f.left = p.left;
                } else {
                    f.right = p.left;
                }
            }
            return;
        }

        //th3: p chỉ có 1 con bên phải
        if (p.left == null && p.right != null) {
            if (f == null) { // p là root
                root = p.right;
            } else {
                if (p == f.left) {
                    f.left = p.right;
                } else {
                    f.right = p.right;
                }
            }
            return;
        }

        //TH4: Node cần xoá có cả  2 node.
        if (p.left != null && p.right != null) {
            // tim node phai nhat của nửa bên trái của p
            Node q = p.left;
            Node frp, rp; // frp là cha rp
            frp = null;
            rp = q;
            while (rp.right != null) {
                frp = rp;
                rp = rp.right;
            }
            // rp là Node phải nhất.
            // coppy giá trị của rp vào nốt cần xoá.
            p.info = rp.info;
            // xoá node rp;
            if (frp == null) {
                p.left = q.left;
            } else {
                frp.right = rp.left;
            }
        }
    }

    void deleByMerging(int x) {
        if (isEmpty()) {
            return;
        }
        Node f, p;
        f = null;
        p = root;
        while (p != null) {
            if (p.info == x) {
                break;
            }
            f = p;
            if (x < p.info) {
                p = p.left;
            } else {
                p = p.right;
            }
        }

        if (p == null) {
            return;
        }
        // tại đây p = nút cần xoá.
        // f là cha của p/

        // TH1: p là node lá
        if (p.left == null && p.right == null) {
            if (f == null) { // p là root
                root = null;
            } else {
                if (p == f.left) {
                    f.left = null;
                } else {
                    f.right = null;
                }
            }
            return;
        }

        //TH2: p có 1 con trái
        if (p.left != null && p.right == null) {
            if (f == null) { // p là root
                root = p.left;
            } else {
                if (p == f.left) {
                    f.left = p.left;
                } else {
                    f.right = p.left;
                }
            }
            return;
        }

        //th3: p chỉ có 1 con bên phải
        if (p.left == null && p.right != null) {
            if (f == null) { // p là root
                root = p.right;
            } else {
                if (p == f.left) {
                    f.left = p.right;
                } else {
                    f.right = p.right;
                }
            }
            return;
        }

        //TH4: Node cần xoá có cả  2 node.
        if (p.left != null && p.right != null) {
            // tim node phai nhat của nửa bên trái của p
            Node q = p.left;
            Node frp, rp; // frp là cha rp
            frp = null;
            rp = q;
            while (rp.right != null) {
                frp = rp;
                rp = rp.right;
            }
            // rp là Node phải nhất.

            rp.right = p.right;
            if (f == null) {
                root = p.left;
            } else if (p == f.left) {
                f.left = p.left;
            } else {
                f.right = p.left;
            }
        }
    }

    int height(Node p) {
        if (p == null) {
            return 0;
        }
        int leftHeight, rightHeight, height;

        leftHeight = height(p.left);
        rightHeight = height(p.right);
	
	if(leftHeight > rightHeight ){
		height = leftHeight;
	}
else{
	height = rightHeight;
}

        height = leftHeight > rightHeight ? leftHeight : rightHeight;
        return height + 1;
    }

    int count(Node p) {
        if (p == null) {
            return 0;
        }
        int k, h, r;
        k = count(p.left);
        h = count(p.right);
        r = k + h + 1;
        return r;
    }

    // add tree vào mảng để cân bằng.
    void inOrder(ArrayList<Integer> t, Node p) {
        if (p == null) {
            return;
        }
        inOrder(t, p.left);
        t.add(p.info);
        inOrder(t, p.right);
    }

    void balance(ArrayList<Integer> t, int i, int j) {
        if (i > j) {
            return;
        }
        int k = (i + j) / 2;
        insert2(t.get(k));
        // insert(t.get(k).info.name, t.get(k).age);
        balance(t, i, k - 1);
        balance(t, k + 1, j);
    }

    void balance() {
        ArrayList<Integer> t = new ArrayList<>();
        inOrder(t, root);
        int n = t.size();
        clear();
        balance(t, 0, n - 1);
    }

    Node rotateRight(Node p) {
        if (p == null || p.left == null) {
            return (p);
        }
        Node q = p.left;
        p.left = q.right;
        q.right = p;
        return (q);
    }

    Node rotateLeft(Node p) {
        if (p == null || p.right == null) {
            return (p);
        }
        Node q = p.right;
        p.right = q.left;
        q.left = p;
        return (q);
    }

//    Node fatherP(int xPrice) {
//        Node f, p;
//        f = null;
//        p = root;
//        while (p != null) {
//            if (p.info.price == xPrice) {
//                break;
//            }
//            f = p;
//            if (xPrice < p.info.price) {
//                p = p.left;
//            } else {
//                p = p.right;
//            }
//        }
//        return (f);
//    }
    Node findFather(Node x) {
        MyQueue q = new MyQueue();
        q.enqueue(root);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            if (r.left == x || r.right == x) {
                return r;
            }
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
        return null;
    }
    
    /**
     * twoSOn = null
     * inOrder2(root)
     * Node father = findfather(twoson);
     * boolean isLeft = (father.left == twoSon)
     * twoSon = rotateLeft(twoSon);
     * if(isLeft){
     *        father.left = twoson;
     */
    // Chú ý inOrder do logic ở giữa đệ quy nên là phải kiểm soát count muốn in. 
}
