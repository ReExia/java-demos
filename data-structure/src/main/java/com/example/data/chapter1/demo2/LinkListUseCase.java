package com.example.data.chapter1.demo2;

import java.util.Scanner;

public class LinkListUseCase {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n,val;

        n = scanner.nextInt();

        LinkList linkList = new LinkList();
        for (int i = 0; i < n ; i++){
            Node q = linkList.head;
            val = scanner.nextInt();
            Node p = new Node(val);

            while(q != null && q.next != null && Integer.valueOf(q.next.data.toString()) < val){
                q = q.next;
            }
            p.next = q.next;
            q.next = p;
        }
        Node k = linkList.head;
        for (int i = 0 ; i < n ; i++){
            k = k.next;
            System.out.println(k.data);
        }
    }
}
