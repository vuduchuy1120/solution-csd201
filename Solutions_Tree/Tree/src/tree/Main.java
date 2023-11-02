/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

/**
 *
 * @author ASUS
 */
public class Main {

    public static void main(String[] args) {
        BSTree bSTree = new BSTree();
        int[] a = new int[]{18,15,10,4,12,22,35,31,44,24,50,25,70,66,90};
        bSTree.insertMany(a);
        System.out.println("check1: ");
        bSTree.breadth(bSTree.root);
        System.out.println("");
        System.out.println("check 2:");
        bSTree.balance();
        bSTree.breadth(bSTree.root);
        System.out.println("");
    }
}
