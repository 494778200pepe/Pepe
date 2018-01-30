package com.hopechart.topic;

/**
 * Created by wang on 2017/6/14.
 */

public class Stack<E> {
    private Object[] objects;
    /**
     * 默认栈容量
     */
    private static final int DEFAULT_SIZE = 10;
    /**
     * 栈容量
     */
    private int capacity;
    /**
     * 栈中的对象数量
     */
    private int count = 0;

    public Stack() {
        this(DEFAULT_SIZE);
    }

    public Stack(int size) {
        if (size <= 0) {
            throw new RuntimeException("初始化大小不能小于或等于0：" + size);
        } else {
            objects = new Object[size];
            capacity = size;
        }
    }

    /**
     * 进栈,第一个元素top=0
     *
     * @param e
     * @return
     */
    public boolean push(E e) {
        if (isFull()) {
            inflate();
        }
        objects[count++] = e;
        return true;
    }

    /**
     * 弹出栈顶元素
     *
     * @return
     */
    public E pop() {
        if (isEmpty()) {
            return null;
        } else {
            return (E) objects[--count];
        }
    }

    /**
     * 查看栈顶元素但不移除
     *
     * @return
     */
    public E peek() {
        if (isEmpty()) {
            return null;
        } else {
            return (E) objects[count - 1];
        }
    }

    /**
     * 栈是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * 栈是否已满
     *
     * @return
     */
    public boolean isFull() {
        return count == capacity;
    }

    public int size() {
        return count;
    }


    /**
     * 扩充栈的大小,为当前栈空间的两倍
     */
    public void inflate() {
        Object[] temp = objects;
        objects = new Object[2 * objects.length];
        capacity *= 2;
        System.arraycopy(temp, 0, objects, 0, temp.length);
    }

    /**
     * 返回堆栈中该位置的对象
     *
     * @param position
     * @return
     */
    public E get(int position) {
        if (isEmpty()) {
            return null;
        } else if (position > count || position < 0) {
            throw new RuntimeException("不在范围内");
        } else {
            return (E) objects[position];
        }
    }

    /**
     * 返回对象在堆栈中的位置，以1为基数,result 为0表示对象不在栈中
     *
     * @param e
     * @return -1,表示栈为空或者没找到
     */
    public int research(E e) {
        if (isEmpty()) {
            return -1;
        }
        int i = count;
        int result = -1;
        while (count != 0) {
            if (peek() != e) {
                count--;
            } else {
                result = count - 1;
                break;
            }
        }
        count = i;
        return result;
    }
}
