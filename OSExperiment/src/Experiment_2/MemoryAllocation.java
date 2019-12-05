package Experiment_2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class Node {
    public int addrrss = 0;
    public int size = 0;
    public int unused = 0;
    Node next = null;


    public Node(int addrrss, int val) {
        this.addrrss = addrrss;
        this.size = val;
        unused = size;
    }

    @Override
    public String toString() {
        return "Partition[" +
                "addrrss=" + addrrss +
                ", size=" + size +
                ", unused=" + unused +
                ']';
    }
}

public class MemoryAllocation {
    public final int G = 5;
    public List<Node> list = new ArrayList<>();

    public MemoryAllocation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入分区块数：");
        int n = scanner.nextInt();
        while(n > 0) {
            System.out.println("请输入分区首地址：");
            int address = scanner.nextInt();
            System.out.println("请输入分区大小：");
            int size = scanner.nextInt();
            list.add(new Node(address, size));
            n--;
        }
    }

    //最佳适应算法
    public void BF(int deal) {
        sort1();
        boolean flag = false;
        for (Node node : list) {
            if (deal <= node.size) {
                flag = true;
                if (node.size - deal > G) {
                    node.unused = node.size - deal;
                } else {
                    node.unused = 0;
                }
                break;
            }
        }
        if (!flag) {
            System.out.println("分配失败！");
        } else {
            System.out.println("分配成功！");
        }
    }
    public void WF(int deal) {
        sort2();

    }

    public void menu() {
        System.out.println("==================");
        System.out.println("请选择功能 ：");
        System.out.println("1.最佳适应算法");
        System.out.println("2.最坏适应算法");
        System.out.println("3.首次适应算法");
        System.out.println("4.循环首次适应算法");
        System.out.println("0.退出");
        System.out.println("==================");
    }

    public void display() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
    }

    public void sort1() {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if(list.get(i).size > list.get(i + 1).size) {
                    list.add(i, list.get(i + 1));
                    list.add(i + 2, list.get(i + 1));
                    list.remove(i + 1);
                    list.remove(i + 2);
                }
            }
        }
    }
    public void sort2() {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if(list.get(i).size < list.get(i + 1).size) {
                    list.add(i, list.get(i + 1));
                    list.add(i + 2, list.get(i + 1));
                    list.remove(i + 1);
                    list.remove(i + 2);
                }
            }
        }
    }



}
