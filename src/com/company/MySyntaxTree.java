package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class MySyntaxTree {
    static MySyntaxTreeNode head=new MySyntaxTreeNode();
    int flag;
    String s;
    String no;

    void createSyntax(){
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入1继续");
        flag=sc.nextInt();
        head.no="99";
        head.k="root";
        while (flag==1){
            sc=new Scanner(System.in);
            System.out.println("请输入语法例如(11 88:1x 33 88:12)");
            s=sc.nextLine();
            System.out.println("请输入编号(两位数)");
            no=sc.next();
            setSyntax(s,no,head);
            sc=new Scanner(System.in);
            System.out.println("请输入1继续");
            flag=sc.nextInt();
        }
        sc.close();
    }
    String[] deleteStr(String[] strings,String s){
        StringBuilder stringBuilder=new StringBuilder();
        int count=0;
        for (String s1:strings){
            if (count==1){
                stringBuilder.append(s1+" ");
            }
            if (count==0){
                if (s1.equals(s)){
                    count++;
                }
            }
        }
        String str=stringBuilder.toString();
        str=str.substring(0,str.length()-1);
        return str.split(" ");
    }
    void setSyntax(String s,String no,MySyntaxTreeNode head){
        String[] ts=s.split(" ");
        MySyntaxTreeNode tail = null;
        MySyntaxTreeNode temp;
        temp=head;
        if (head.left[0]==null){
            tail=toBeList(head,ts);
            tail.no=no;
        }else {
            for (String str:ts){
                for (MySyntaxTreeNode tt:temp.left){
                    if (str.equals(tt.k)){
                        ts=deleteStr(ts,str);
                        temp=tt;
                    }else {
                        tail=toBeList(temp,ts);
                        tail.no=no;
                    }
                    break;
                }
                if (tail!=null){
                    break;
                }
            }
        }
    }
    int chooseNode(MySyntaxTreeNode yy){
        for (int i = 0; i < 20; i++) {
            if (yy.left[i]==null){
                return i;
            }
        }
        return -1;
    }
    MySyntaxTreeNode toBeList(MySyntaxTreeNode head,String[] s){
        MySyntaxTreeNode tail=new MySyntaxTreeNode();
        head.left[chooseNode(head)]=tail;
        MySyntaxTreeNode temp=null;
        for (String ss:s){
            if (tail.k != null) {
                temp = new MySyntaxTreeNode();
                tail.left[0] = temp;
                tail = temp;
            }
            tail.k=ss;
        }
        return tail;
    }
}
