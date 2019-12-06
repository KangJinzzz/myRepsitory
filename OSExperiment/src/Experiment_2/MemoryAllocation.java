package Experiment_2;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class Node {
    public int addrrss = 0;
    public int size = 0;
    Node next = null;


    public Node(int addrrss, int val) {
        this.addrrss = addrrss;
        this.size = val;
    }

    @Override
    public String toString() {
        return "Partition[" +
                "addrrss=" + addrrss +
                ", size=" + size +
                ']';
    }
}

public class MemoryAllocation {
    public final int G = 5;
    public List<Node> list = new LinkedList<>();


    public void initialize() {
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
    public Node BF(int deal) {
        sort1();
        boolean flag = false;
        Node cur = null;
        for (Node node : list) {
            if (node.size >= deal) {
                flag = true;
                cur = node;
                if (node.size - deal > G) {
                    node.size = node.size - deal;
                } else {
                    node.size = 0;
                }
                break;
            }
        }
        if (!flag) {
            System.out.println("分配失败！");
            return null;
        } else {
            System.out.println("分配成功！");
            return cur;
        }
    }

    //最坏适应算法
    public Node WF(int deal) {
        sort2();
        Node cur = null;
        boolean flag = false;
        Node node = list.get(0);
        if(node.size >= deal) {
            flag = true;
            cur = node;
            if (node.size - deal > G) {
                node.size = node.size - deal;
            } else {
                node.size = 0;
            }
        }

        if (!flag) {
            System.out.println("分配失败！");
            return null;
        } else {
            System.out.println("分配成功！");
            return cur;
        }
    }

    //首次适应算法
    public Node FF(int deal) {
        sort3();
        boolean flag = false;
        Node cur = null;
        for (Node node : list) {
            if (node.size >= deal) {
                flag = true;
                cur = node;
                if (node.size - deal > G) {
                    node.size = node.size - deal;
                } else {
                    node.size = 0;
                }
                break;
            }
        }
        if (!flag) {
            System.out.println("分配失败！");
            return null;

        } else {
            System.out.println("分配成功！");
            return cur;
        }
    }

    //循环首次试应算法
    public Node NF(int deal, Node prev) {
        sort3();
        Node cur = prev.next;
        while (true) {
            if(cur == prev) {
                if(cur.size >= deal) {
                    if (cur.size - deal > G) {
                        cur.size = cur.size - deal;
                    } else {
                        cur.size = 0;
                    }
                    return cur;
                } else {
                    return null;
                }
            } else if(cur.size >= deal) {
                if (cur.size - deal > G) {
                    cur.size = cur.size - deal;
                } else {
                    cur.size = 0;
                }
                return cur;
            }
            if(cur.next == null) {
                cur = list.get(0);
            } else {
                cur = cur.next;
            }
        }
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
                if(list.get(j).size > list.get(j + 1).size) {
                    list.add(j, list.get(j + 1));
                    list.add(j + 2, list.get(j + 1));
                    list.remove(j + 1);
                    list.remove(j + 2);
                }
            }
        }
    }

    public void sort2() {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if(list.get(j).size < list.get(j + 1).size) {
                    list.add(j, list.get(j + 1));
                    list.add(j + 2, list.get(j + 1));
                    list.remove(j + 1);
                    list.remove(j + 2);
                }
            }
        }
    }

    public void sort3() {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if(list.get(j).addrrss > list.get(j + 1).addrrss) {
                    list.add(j, list.get(j + 1));
                    list.add(j + 2, list.get(j + 1));
                    list.remove(j + 1);
                    list.remove(j + 2);
                }
            }
        }
    }



}
