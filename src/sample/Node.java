package sample;

import java.util.ArrayList;

public class Node{
    int[][] value = new int[3][3];
    int zi, zj, h;
    double H=0, F;
    Node parent;
    ArrayList<Node> child = new ArrayList<Node>(); //Нащадки вузла

    Node(){}

    Node(int[][] a){
        for(int i=0; i<a.length; i++){
            for (int j=0; j<a[i].length; j++) {
                value[i][j]=a[i][j];
            }
        }
    }

    Node(int[][] a, int h, Node p){
        for(int i=0; i<a.length; i++){
            for (int j=0; j<a[i].length; j++) {
                value[i][j]=a[i][j];
                this.h=h;
                this.parent=p;
            }
        }
    }

    Node(Node another) {
        this.h=another.h+1;
    }

    double length(int i, int j){
        //Відстань міських кварталів
        return Math.abs(i-(value[i][j]-1)/value.length)+Math.abs(j-(value[i][j]-1)%value.length);
        //Евклідова відстань
        //return Math.sqrt(Math.pow(i-(value[i][j]-1)/value.length, 2) + Math.pow(j-(value[i][j]-1)%value.length, 2));
        //Відсань Чебишева
        //return Math.max(Math.abs(i-(value[i][j]-1)/value.length), Math.abs(j-(value[i][j]-1)%value.length));
    }

    public double getH() {
        this.H=0;
        for (int i=0; i<value.length; i++)
            for (int j=0; j<value.length; j++)
                if(i*4+j+1!=value[i][j]&&value[i][j]!=0)
                    this.H+=this.length(i, j);
        return this.H;
    }

    public double getF() {
        F=getH()+h;
        return F;
    }

    void searchZero(){
        for(int i=0; i<this.value.length; i++){
            for (int j=0; j<this.value[i].length; j++){
                if(this.value[i][j]==0){
                    this.zi=i;
                    this.zj=j;
                }
            }
        }
    }

    Node setValue(int[][] val){
        value=val;
        return this;
    }

    int[][] getValue(){
        return value;
    }


    void print(){
        for(int i=0; i<value.length; i++){
            for (int j=0; j<value[i].length; j++) {
                System.out.print(value[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("------------- G=" + h + "  H=" + getH() + "  F=" + getF());
    }

    void addchild(){
        try{
            this.moveright();
            this.child.add(new Node(this.value, this.h+1, this));
            this.moveleft();
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
            this.movedown();
            this.child.add(new Node(this.value, this.h+1, this));
            this.moveup();
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
            this.moveleft();
            this.child.add(new Node(this.value, this.h+1, this));
            this.moveright();
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
            this.moveup();
            this.child.add(new Node(this.value, this.h+1, this));
            this.movedown();
        }catch(ArrayIndexOutOfBoundsException e){}
    }

    Node moveup(){
        this.searchZero();
        value[zi][zj]=value[zi-1][zj];
        value[zi-1][zj]=0;
        return this;
    }

    Node movedown(){
        this.searchZero();
        value[zi][zj]=value[zi+1][zj];
        value[zi+1][zj]=0;
        return this;
    }

    Node moveleft(){
        this.searchZero();
        value[zi][zj]=value[zi][zj-1];
        value[zi][zj-1]=0;
        return this;
    }

    Node moveright(){
        this.searchZero();
        value[zi][zj]=value[zi][zj+1];
        value[zi][zj+1]=0;
        return this;
    }
}