package com.pepe.generic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型Demo
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_btn1://泛型的出现
                test1();
                break;
            case R.id.main_btn2://什么是泛型
                test2();
                break;
            case R.id.main_btn3://自定义泛型
                test3();
                break;
            case R.id.main_btn4://泛型的类型
                test4();
                break;
            case R.id.main_btn5://类型通配符1
                test5();
                break;
            case R.id.main_btn6://类型通配符2
                test6();
                break;
            case R.id.main_btn7://<? extends T>
                test7();
                break;
            case R.id.main_btn8://<? super T>
                test8();
                break;

        }
    }

    /**
     * 泛型存在的原因
     */
    public void test1() {
        List list = new ArrayList();
        list.add("qqyumidi");
        list.add("corn");
        list.add(100);

        for (int i = 0; i < list.size(); i++) {
            String name = (String) list.get(i); // 1
            System.out.println("name:" + name);
        }
    }

    /**
     * 泛型的使用
     */
    public void test2() {
        /*
        List list = new ArrayList();
        list.add("qqyumidi");
        list.add("corn");
        list.add(100);
        */

        List<String> list = new ArrayList<String>();
        list.add("qqyumidi");
        list.add("corn");
//        list.add(100);   // 1  提示编译错误

        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i); // 2
            System.out.println("name:" + name);
        }
    }


    public void test3() {

        Box<String> name = new Box<String>("corn");
        System.out.println("name:" + name.getData());
    }

    /**
     * 一个简单的泛型类
     * @param <T>
     */
    class Box<T> {

        private T data;

        public Box() {

        }

        public Box(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }
    }

    /**
     * 泛型类型在逻辑上看以看成是多个不同的类型，实际上都是相同的基本类型
     */
    public void test4() {
        Box<String> name = new Box<String>("corn");
        Box<Integer> age = new Box<Integer>(712);

        System.out.println("name class:" + name.getClass());      // com.qqyumidi.Box
        System.out.println("age class:" + age.getClass());        // com.qqyumidi.Box
        System.out.println(name.getClass() == age.getClass());    // true
    }


    public void test5() {

        Box<Number> name = new Box<Number>(99);
        Box<Integer> age = new Box<Integer>(712);

        getData1(name);

        //The method getData(Box<Number>) in the type GenericTest is
        //not applicable for the arguments (Box<Integer>)
//        getData1(age);   // 1


        Box<Integer> a = new Box<Integer>(712);
//        Box<Number> b = a;  // 1
        Box<Float> f = new Box<Float>(3.14f);
//        b.setData(f);        // 2

    }

    public void getData1(Box<Number> data) {
        System.out.println("data :" + data.getData());
    }

    /**
     * 使用 ? 代替具体的类型实参
     */
    public void test6() {

        Box<String> name = new Box<String>("corn");
        Box<Integer> age = new Box<Integer>(712);
        Box<Number> number = new Box<Number>(314);

        getData2(name);
        getData2(age);
        getData2(number);
    }

    public void getData2(Box<?> data) {
        System.out.println("data :" + data.getData());
    }

    static class Food {
    }
    static class Fruit extends Food {
    }
    static class Apple extends Fruit {
    }
    static class RedApple extends Apple {
    }

    /**
     * 如果你想从一个数据类型里获取数据，使用 ? extends 通配符
     * 如果你想把对象写入一个数据结构里，使用 ? super 通配符
     * 如果你既想存，又想取，那就别用通配符。
     */
    public void test7() {
        List<? extends Fruit> flist = new ArrayList<Apple>();
//        complie error:
//        flist.add(new Apple());
//        flist.add(new Fruit());
//        flist.add(new Object());
        flist.add(null); // only work for null

        Fruit fruit = flist.get(0);
        Apple apple = (Apple) flist.get(0);

        flist.contains(new Fruit());
        flist.contains(new Apple());
    }

    /**
     * 如果你想从一个数据类型里获取数据，使用 ? extends 通配符
     * 如果你想把对象写入一个数据结构里，使用 ? super 通配符
     * 如果你既想存，又想取，那就别用通配符。
     */
    public void test8() {
        List<? super Fruit> flist = new ArrayList<Fruit>();
        flist.add(new Fruit());
        flist.add(new Apple());
        flist.add(new RedApple());

//        compile error:
//        List<? super Fruit> flist = new ArrayList<Apple>();
//        compile error:
//        Fruit item = flist.get(0);
    }


}
