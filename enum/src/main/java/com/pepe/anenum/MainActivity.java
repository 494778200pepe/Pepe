package com.pepe.anenum;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends Activity {

//    public enum MyColor implements Behaviour{
//        RED,GREEN,BLUE;
//        private final String name="fsd";
//
//        // 接口方法
//        @Override
//        public String getInfo() {
//            return this.name;
//        }
//
//        // 接口方法
//        @Override
//        public void print() {
//            Log.d("pepe","name:" + this.name);
//        }
//
//    }

    public enum MyColor implements Behaviour{
        RED{
            @Override
            public String getInfo() {
                return this.toString();
            }

            @Override
            public void print() {
                Log.d("pepe","name:" + this.toString());
            }
        },GREEN{
            @Override
            public String getInfo() {
                return this.toString();
            }

            @Override
            public void print() {
                Log.d("pepe","name:" + this.toString());
            }
        },BLUE{
            @Override
            public String getInfo() {
                return this.toString();
            }

            @Override
            public void print() {
                Log.d("pepe","name:" + this.toString());
            }
        };
        private final String name="fsd";
    }

    public enum EnumTest {
        MON, TUE, WED{
            @Override
            public String toString() {
                return "Today is Wednesday!";
            }
        }, THU, FRI, SAT, SUN;
    }

    public enum Enumtestt {
        MON(1), TUE(2), WED(3), THU(4), FRI(5), SAT(6) {
            @Override
            public boolean isRest() {
                return true;
            }
        },
        SUN(0) {
            @Override
            public boolean isRest() {
                return true;
            }
        };

        private int value;

        private Enumtestt(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public boolean isRest() {
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(MyColor myColor: MyColor.values()){
            Log.d("pepe",myColor.toString());
            Log.d("pepe",myColor.name());
        }

        //EnumTest
        EnumTest test = EnumTest.TUE;

        //compareTo(E o)
        switch (test.compareTo(EnumTest.MON)) {
            case -1:
                Log.d("pepe","TUE 在 MON 之前");
                break;
            case 1:
                Log.d("pepe","TUE 在 MON 之后");
                break;
            default:
                Log.d("pepe","TUE 与 MON 在同一位置");
                break;
        }

        //遍历枚举
        Log.d("pepe","遍历ColorEnum枚举中的值");
        for(EnumTest color : EnumTest.values()){
            Log.d("pepe","遍历toString："+color.toString()+"---ordinal值："+color.ordinal());
        }

        //valueOf,由"WED"获取枚举值
        EnumTest enumTest= EnumTest.valueOf("WED");
        Log.d("pepe","由\"WED\"获取枚举值:"+enumTest.toString());

        //values(),获取枚举的个数
        Log.d("pepe","ColorEnum枚举中的值有"+ EnumTest.values().length+"个");


        //getDeclaringClass()
        Log.d("pepe","getDeclaringClass(): " + test.getDeclaringClass().getName());

        //name() 和  toString()
        Log.d("pepe","name(): " + test.name());
        Log.d("pepe","toString(): " + test.toString());

        //ordinal()， 返回值是从 0 开始
        Log.d("pepe","ordinal(): " + test.ordinal());



        //Enumtest
        Log.d("pepe","Enumtestt.SAT 的 isRest = " + Enumtestt.SAT.isRest());



        // EnumSet的使用
        EnumSet<EnumTest> weekSet = EnumSet.allOf(EnumTest.class);
        for (EnumTest day : weekSet) {
            Log.d("pepe",day.toString());
        }

        // EnumMap的使用
        EnumMap<EnumTest, String> weekMap = new EnumMap(EnumTest.class);
        weekMap.put(EnumTest.MON, "星期一");
        weekMap.put(EnumTest.TUE, "星期二");
        // ... ...
        for (Iterator<Map.Entry<EnumTest, String>> iter = weekMap.entrySet().iterator(); iter.hasNext();) {
            Map.Entry<EnumTest, String> entry = iter.next();
            Log.d("pepe",entry.getKey().name() + ":" + entry.getValue());
        }
    }
}
