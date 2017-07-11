package com.pepe.anenum;

/**
 * Created by pepe on 2016/7/5.
 * E_mail: 494778200@qq.com
 * Company:小知科技 http://www.zizizizizi.com/
 */
public enum Season2 {
    //1.必须在枚举类的第一行写出有哪些枚举值
    SPRING("春天", "春风又绿江南岸"),
    SUMMER("夏天", "映日荷花别样"),
    FALL("秋天", "秋水共长天一色"),
    WINTER("冬天", "窗含西岭千秋雪");

    private final String name;
    private final String desc;

    private Season2(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
