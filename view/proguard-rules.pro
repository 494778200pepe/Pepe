# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\install\Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:
#三种语法：保留类名，保留方法名，保留类名和方法名
-keepclasswithmembernames class *{#保留native的方法的方法名和包含native方法的类名不变
    native <methods>;
}

-keepclassmenmbers public class * extends android.view.View{#保留继承于View的类中set*和get*方法名不变
    void set*(***);
    *** get*();
}

-keepclassmembers class * extends android.app.Activity{#保留继承于Activity的类中以View为参数的方法名不变
    public void *(android.view.VIew);
}

-keep class * implements android.os.Parcelable{#保留实现了Parcelable接口的类的类名以及Parcelable
    public static final android.os.Parcelable$Createor *;
}

-keepclassmembers class **.R$*{#保留R$*类中静态字段的字段名
    public static <fields>;
}

#去除log
-assumenosideeffects class android.util.Log{
    public static boolean isLoggable(java.lang.String,int);
    public static int v(...);
    public static int i(...);
    public static int d(...);
    public static int w(...);
    public static int e(...);
}

-assumenosideeffects class com.example.log.Logger{
    public static int v(...);
    public static int i(...);
    public static int d(...);
    public static int w(...);
    public static int e(...);
}


# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
