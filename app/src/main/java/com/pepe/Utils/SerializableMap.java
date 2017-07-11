package com.pepe.Utils;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by pepe on 2016/6/2.
 * E_mail: 494778200@qq.com
 * 可以通过bundle传递的map
 */
public class SerializableMap<E, V> implements Serializable {

    private Map<E, V> map;

    public Map<E, V> getMap() {
        return map;
    }

    public void setMap(Map<E, V> map) {
        this.map = map;
    }
}