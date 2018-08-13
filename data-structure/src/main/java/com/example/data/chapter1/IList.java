package com.example.data.chapter1;

/**
 * 线性表接口
 */
public interface IList {

    /**
     * 将线性表清空
     */
    public void clear();

    /**
     * 判断线性表是否为空
     * @return
     */
    public boolean isEmpty();

    /**
     * 返回线性表长度
     * @return
     */
    public int length();

    /**
     * 读取并且返回线性表的第i个元素
     * @param i
     * @return
     */
    public Object get(int i) throws Exception;

    /**
     * 插入x作为第i个元素
     * @param i
     * @param x
     */
    public void insert(int i, Object x) throws Exception;

    /**
     * 删除第i个元素
     * @param i
     */
    public void remove(int i) throws Exception;

    /**
     * 返回元素x首次出现的位序号
     * @param x
     * @return
     */
    public int indexOf(Object x);

    /**
     * 输出线性表中各个元素的值
     */
    public void display();
}
