package graph;

public class Graph {

    int[][] a;   // Ma trận kề
    int n;
    char[] v;  // đỉnh của graph

    Graph() {
        v = "ABCDEFGHIJKLMN".toCharArray();
    }

    void setData(int[][] b) {
        n = b.length;
        int i, j;
        a = new int[n][n];
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                a[i][j] = b[i][j];
            }
        }
    }

    void dispAdj() {
        int i, j;
        for (i = 0; i < n; i++) {
            System.out.println();
            for (j = 0; j < n; j++) {
                System.out.printf("%4d", a[i][j]);
            }
        }
    }

    void visit(int i) {
        System.out.print(v[i] + "  ");
    }

    // en : kiểm tra xem đã thêm vào hàng đợi hay chưa.
    void breadth(boolean[] en, int i) {
        MyQueue q = new MyQueue();
        q.enqueue(i);
        en[i] = true; // đánh dấu đã thêm vào hàng đợi
        int r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            visit(r);
            // thêm các đỉnh kề với đỉnh r. 
            for (int j = 0; j < n; j++) {
                if (!en[j] && a[r][j] > 0) {
                    q.enqueue(j);
                    en[j] = true;
                }
            }
            
        }
    }

    void breadth(int k) {
        boolean[] en = new boolean[n];
        int i, j;
        for (i = 0; i < n; i++) {
            en[i] = false; // khởi tạo giá trị cho mảng en
        }
        breadth(en, k);
        // có thể có nhiều thành phần không liên thông. Nên phải kiểm tra xem còn cái nào chưa được duyệt.
        for (i = 0; i < n; i++) {
            if (en[i] == false) {
                breadth(en, i);
            }
        }

    }

    void depth(boolean[] vis, int i) {
        visit(i);
        vis[i] = true;
        for (int j = 0; j < n; j++) {
            if (vis[j] == false  && a[i][j] > 0) {
                depth(vis, j);
            }
        }
    }

    void depth(int k) {
        boolean[] vis = new boolean[n];
        int i, j;
        for (i = 0; i < n; i++) {
            vis[i] = false;
        }
        depth(vis, k);
        for (i = 0; i < n; i++) {
            if (!vis[i]) {
                depth(vis, i);
            }
        }
    }

    void dijkstra(int fro, int to) {
        int INF = 99;
        boolean[] S = new boolean[n];
        int[] d = new int[n]; // khoảng cách ngắn nhất từ gốc đến đích
        int[] p = new int[n]; // lưu lại cách đỉnh của đường đi ngắn nhất
        int i, j, k, min;
        for (i = 0; i < n; i++) {
            S[i] = false;
            d[i] = a[fro][i]; // khoảng cách từ front đến i
            p[i] = fro;
        }
        S[fro] = true;// chọn đỉnh fro.
        while (true) {
            // tìm k sao cho d[k] min.
            min = INF;
            k = -1;
            for (i = 0; i < n; i++) {
                if (S[i]) { // dỉnh nào được chọn rồi thì bỏ qua.
                    continue;
                }
                if (d[i] < min) {
                    min = d[i];
                    k = i;
                }
            }
            if (k == -1) {
                System.out.println("No solution");
                return;
            }
            // select k into the set S
            S[k] = true;
            if (k == to) { // nếu gặp đỉnh đích thì break
                break;
            }
            // update data
            // kiểm tra xem đi thẳng hay đi vòng thì ngắn hơn.
            for (i = 0; i < n; i++) {
                if (S[i]) {
                    continue;
                }
                if (d[i] > d[k] + a[k][i]) {
                    d[i] = d[k] + a[k][i];
                    p[i] = k;
                }
            }
        }
        System.out.println("The shortest distance is " + d[to]);
        MyStack s = new MyStack();
        i = to;
        while (true) {
            s.push(i);
            if (i == fro) {
                break;
            }
            i = p[i];
        }
        i = s.pop();
        System.out.println("Th shotest path is");
        System.out.print(i);
        while (!s.isEmpty()) {
            i = s.pop();
            System.out.print(" -> " + i);
        }
        System.out.println();
    }
}
