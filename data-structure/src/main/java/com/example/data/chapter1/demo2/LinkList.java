package com.example.data.chapter1.demo2;

import com.example.data.chapter1.IList;

import java.util.Scanner;

public class LinkList implements IList {

    public Node head;

    public LinkList(){
        head = new Node();
    }

    public LinkList(int n, boolean Order) throws Exception {
        this();
        if (Order){
            create1(n);
        }else {
            create2(n);
        }
    }

    //尾插法顺序建立单链表
    private void create1(int n) throws Exception {
        Scanner sc = new Scanner(System.in);
        for (int i = 0 ; i < n ; i++){
            insert(0, sc.next());
        }
    }

    //头插法逆序建立单链表
    private void create2(int n) throws Exception {
        Scanner sc = new Scanner(System.in);
        for (int i = 0 ; i < n ; i++){
            insert2(length(), sc.next());
        }
    }

    @Override
    public void clear() {
        head.data = null;
        head.next = null;
    }

    @Override
    public boolean isEmpty() {
        return head.next == null;
    }

    @Override
    public int length() {
        Node p = head.next;
        int length = 0;
        while (p != null){
            p = p.next;
            length++;
        }
        return length;
    }

    @Override
    public Object get(int i) throws Exception {
        Node p = head.next;
        int j;
        for (j = 0; j < i && p != null; j++){
            p = p.next;
        }
        if (j > i || p == null){
            throw new Exception("第" + i + "个数据元素不存在");
        }
        return p.data;
    }

    /**
     * 带头结点的插入
     * @param i
     * @param x
     * @throws Exception
     */
    @Override
    public void insert(int i, Object x) throws Exception {
        Node p = head;
        int j = -1;
        while (p != null && j < i - 1){
            p = p.next;
            j++;
        }
        if (j > i - 1 || p == null){
            throw new Exception("插入位置不合法");
        }
        Node s = new Node(x);
        s.next = p.next;
        p.next = s;
    }

    public void insert2(int i, Object x) throws Exception {
        Node p = head;
        int j = -1;
        while (p != null && j < i -1){
            p = p.next;
            j++;
        }
        if (j > i - 1 || p ==null){
            throw new Exception("插入位置不合法");
        }
        Node s = new Node(x);
        if (i == 0){
            s.next = head;
            head = s;
        }else {
            s.next = p.next;
            s.next = s;
        }
    }



    @Override
    public void remove(int i) throws Exception {
        Node p = head;
        int j = -1;
        while (p != null && j < i -1){
            p = p.next;
            j++;
        }
        if (j > i -1 || p.next == null){
            throw new Exception("删除位置不合法");
        }
        p.next = p.next.next;
    }

    @Override
    public int indexOf(Object x) {
        Node p = head.next;
        int j = 0;
        while (p != null && !p.data.equals(x)){
            p = p.next;
            j++;
        }
        if (p != null){
            return j;
        }else {
            return -1;
        }
    }

    @Override
    public void display() {
        Node p = head.next;
        while (p != null){
            System.out.println(p.data + " ");
            p = p.next;
        }
    }
}
