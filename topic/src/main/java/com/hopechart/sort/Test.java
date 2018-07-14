package com.hopechart.sort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author wang
 * @date 2018/5/16.
 */

public class Test {

    public static void main(String[] args) {
        ArrayList<String> strList = new ArrayList<>();
        strList.add("pepe1");
        strList.add("pepe2");
        strList.add("pepe3");
        printCollection(strList);

        List<Shape> shapes = new ArrayList<Shape>();
        List<? super Circle> cicleSupers = shapes;
        cicleSupers.add(new Circle()); //OK, subclass of Cicle also OK
        cicleSupers.add(new Circle()); //OK, subclass of Cicle also OK
        Object obj = cicleSupers.get(0);
        List<? super Shape> shapeList = new ArrayList<Object>();
        Object shape = shapeList.get(0);
    }

    //使用泛型
    public static void printCollection(Collection<?> c) {
        for (Object e : c) {
            System.out.println(e);
        }
    }
}
