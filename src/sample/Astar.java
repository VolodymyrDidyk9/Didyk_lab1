package sample;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Astar {

    static long time, before, after;

    static Node temp = new Node();

    static int h;
    static ArrayList<Node> res = new ArrayList<>(20);

    static ArrayList<Node> close =new ArrayList<>();  //вершини, перевірені алгоритмом
    static Comparator<Node> comparator = new NodeComparator();
    static PriorityQueue<Node> open = new PriorityQueue<>(comparator); //вершини, ще не перевірені алгоритмом


    //Додавання елемента в відкритий список
    static void add_open(Node n){
        for (int i=0; i<close.size(); i++)
            if(Main.equals(n.value, close.get(i).value))
                return;
        else open.add(n);
    }

    static boolean check(Node n){
        for (int i=0; i<close.size(); i++)
            if(Main.equals(n.value, close.get(i).value))
                return true;
        return false;
    }

    public static int[][] getNext(){
        if(h>0)
            h--;
        return res.get(h).value;
    }

    public static void start(Node start){
        before = System.currentTimeMillis();

        if(!res.isEmpty())
            res.clear();
        open.add(start);
        temp=start;
        while (true){
            //Витяг елемента (стан поля) з вершини відкритого списку
            temp=open.remove();
            if(check(temp))
                continue;

            //Додавання елемента в закритий список
            close.add(temp);
            //Перевірка, чи є елемент розвязком
            if(temp.getH()==0)
                break;
            //Генерування нащадків
            temp.addchild();
            //Додавання нащадків в відкритий список
            for (int i=0; i<temp.child.size(); i++)
                add_open(temp.child.get(i));
        }
        h=temp.h;
        for (int i = 0; i<h; i++)
        {
            res.add(temp);
            temp=temp.parent;
        }

        after = System.currentTimeMillis();
        time = after - before;
        close.clear();
        open.clear();
    }

}
