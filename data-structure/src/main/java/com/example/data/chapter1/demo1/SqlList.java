package com.example.data.chapter1.demo1;

import com.example.data.chapter1.IList;

/**
 * 顺序表实现
 */
public class SqlList implements IList {

    //顺序表的存储空间
    private Object[] listItem;

    //顺序表的当前长度
    private int curLen;

    //顺序表的最大长度
    private int maxSize;

    public SqlList(int maxSize){
        this.curLen = 0;
        this.maxSize = maxSize;
        this.listItem = new Object[maxSize];
    }

    @Override
    public void clear() {
        this.curLen = 0;
    }

    @Override
    public boolean isEmpty() {
        return this.curLen == 0;
    }

    @Override
    public int length() {
        return this.curLen;
    }

    @Override
    public Object get(int i) throws Exception {
        if (i < 0 || i > this.curLen -1){
            throw new Exception("第[" + i + "]个数据元素不存在");
        }
        return this.listItem[i];
    }

    @Override
    public void insert(int i, Object x) throws Exception {
        //1.判断线性表是否达到最大长度
        if (this.curLen == maxSize){
            throw new Exception("顺序表已满");
        }
        //2.判断线性表插入位置
        if (i < 0 || i > curLen){
            throw new Exception("插入位置不合法");
        }

        //将插入位置及之后的、所有元素后移
        for (int j = this.curLen ; j > i ; j--){
            this.listItem[j] = this.listItem[j-1];
        }
        this.listItem[i] = x;
        this.curLen++;
    }

    @Override
    public void remove(int i) throws Exception {
        if (i < 0 || i > this.curLen -1){
            throw new Exception("删除位置[" + i + "]非法");
        }
        //将需要删除的元素赋值为后一个元素，相当于去掉第i个元素，后面的元素往前移动
        for (int j = i; i < this.curLen -1 ; i++){
            this.listItem[j] = this.listItem[j+1];
        }
        this.curLen--;
    }

    @Override
    public int indexOf(Object x) {
        for (int i = 0 ; i <= this.curLen -1 ; i++){
            if (this.listItem[i].equals(x)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public void display() {
        for (int i = 0 ; i < this.curLen -1 ; i++){
            System.out.print("["+ this.listItem[i] + "]");
        }
    }
}
