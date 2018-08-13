package com.example.data.chapter1.demo1;

/**
 * 顺序表应用
 */
public class SqlListUseCase2 {

    public static void main(String[] args) throws Exception {

        SqlList sqlList = new SqlList(5);

        sqlList.insert(0, 89);
        sqlList.insert(1, 93);
        sqlList.insert(2, 92);
        sqlList.insert(3, 90);
        sqlList.insert(4,100);

        int result = sqlList.indexOf(90);

        if (result == -1){
            System.out.println("顺序表中不存在成绩为90的元素");
        }else {
            System.out.println("顺序表中成绩为90的数据元素位置为 : " + result);
        }

    }

}
