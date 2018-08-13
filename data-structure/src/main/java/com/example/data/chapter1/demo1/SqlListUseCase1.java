package com.example.data.chapter1.demo1;

import java.util.Scanner;

/**
 * 顺序表应用
 */
public class SqlListUseCase1 {

    public static void main(String[] args) throws Exception {
        SqlList sqlList = new SqlList(26);
        for (int i = 0; i < 26; i++){
            sqlList.insert(i,  (char) ('a' + i));
        }

        while (true){
            System.out.println("请输入需要查询的元素位序号:");

            int input = new Scanner(System.in).nextInt();

            if (input == 0){
                System.out.println("第" + input + "个元素的直接前驱不存在");
                System.out.println("第" + input + "个元素的直接后继为 : " + sqlList.get(input + 1));
            }

            if (input > 0 && input < 25){
                System.out.println("第" + input + "个元素的直接前驱为 : " + sqlList.get(input - 1));
                System.out.println("第" + input + "个元素的直接后继为 : " + sqlList.get(input + 1));
            }

            if (input >= 25){
                System.out.println("第" + input + "个元素的直接后继不存在");
                System.out.println("第" + input + "个元素的直接前驱为 : " + sqlList.get(input - 1));
            }
        }

    }

}
