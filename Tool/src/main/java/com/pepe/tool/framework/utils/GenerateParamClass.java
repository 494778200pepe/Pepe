package com.pepe.tool.framework.utils;

import android.content.Context;

import com.android.dx.Code;
import com.android.dx.DexMaker;
import com.android.dx.FieldId;
import com.android.dx.Local;
import com.android.dx.MethodId;
import com.android.dx.TypeId;
import com.zitech.framework.BaseApplication;
import com.zitech.framework.data.network.Constants;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * Created by hsj on 2016/7/6.
 */
public class GenerateParamClass {

   public static Object generateObject(Map params){
       Object instance = null;
       try {
           DexMaker dexMaker = new DexMaker();
           // Generate a HelloWorld class.
           TypeId<?> classparams = TypeId.get("LParams;");
           dexMaker.declare(classparams, "Params.generated", Modifier.PUBLIC, TypeId.OBJECT);
           generateConstruct(dexMaker, classparams);
           Iterator<Map.Entry> entries = params.entrySet().iterator();
           while (entries.hasNext()) {
               Map.Entry entry = entries.next();
               FieldId fieldId =  generateField(dexMaker, classparams,String.valueOf(entry.getKey()),TypeId.get(entry.getValue().getClass()));
               generateSet(dexMaker,classparams,fieldId);
               generateGet(dexMaker,classparams,fieldId);
           }
           // Create the dex file and load it.
           try {
               File dexOutputDir = Constants.context.getDir("dex", 0);
               ClassLoader loader = dexMaker.generateAndLoad(BaseApplication.class.getClassLoader(),
                       dexOutputDir);
               Class<?> helloWorldClass = loader.loadClass("Params");
               // Execute our newly-generated code in-process.
               // 通过反射查看所有方法名
               Constructor constructor = helloWorldClass.getConstructor(int.class);
               instance = constructor.newInstance(1);
//               Method[] methods = helloWorldClass.getMethods();
//               for (int i = 0; i < methods.length; i++) {
//                   System.out.println(methods[i].getName());
//               }
           } catch (Exception e) {
               e.printStackTrace();
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
        return instance;
   }

    private static void generateConstruct(DexMaker dexMaker, TypeId<?> declaringType){
        MethodId construct = declaringType.getConstructor(TypeId.INT);
        Code code = dexMaker.declare(construct, Modifier.PUBLIC);
        Local thisRef = code.getThis(declaringType);
        code.invokeDirect(TypeId.OBJECT.getConstructor(), null, thisRef);
        code.returnVoid();

    }
    private static FieldId generateField(DexMaker dexMaker, TypeId<?> declaringType,String name,TypeId typeId){
        FieldId fieldId = declaringType.getField(typeId,name);
        dexMaker.declare(fieldId,Modifier.PUBLIC,null);

        return fieldId;
    }


    private static void generateSet(DexMaker dexMaker, TypeId<?> declaringType,FieldId fieldId){
        MethodId set = declaringType.getMethod(TypeId.VOID,"set" + conver(fieldId.getName()),fieldId.getType());
        Code code = dexMaker.declare(set,  Modifier.PUBLIC);
        //代表第一个参数
        Local localparam = code.getParameter(0,fieldId.getType());
        //this操作符
        Local thisRef = code.getThis(declaringType);
        //赋值操作
        code.iput(fieldId,thisRef,localparam);
        code.returnVoid();
    }

    private static void generateGet(DexMaker dexMaker, TypeId<?> declaringType,FieldId fieldId){
        MethodId get = declaringType.getMethod(fieldId.getType(),"get" + conver(fieldId.getName()));
        Code code = dexMaker.declare(get, Modifier.PUBLIC);
        Local result = code.newLocal(fieldId.getType());
        code.sget(fieldId,result);
        code.returnValue(result);
    }

    public static String conver(String name){
        String first = name.substring(0, 1).toUpperCase();
        String rest = name.substring(1, name.length());
        String newStr = new StringBuffer(first).append(rest).toString();
        return newStr;
    }

    public static void searchAndinVokeMethod(Object object,String name,Object value){
        try {
            Method[] methods = object.getClass().getMethods();
            int length = methods.length;
            for(int i = 0;i<length;i++){
                Method method = methods[i];
                String methodName = "set" + GenerateParamClass.conver(name);
                if(method.getName().equals(methodName)){
                    method.invoke(object,value);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Object generate(Map params){
        Object object =  generateObject(params);
        //对这个对象调用set方法;
        Iterator<Map.Entry> entries = params.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Integer, Integer> entry = entries.next();
            GenerateParamClass.searchAndinVokeMethod(object,String.valueOf(entry.getKey()),entry.getValue());
        }
        if(object==null){
            object = new Object();
        }
        return object;
    }
}
