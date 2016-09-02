package com.zmr.xuxiaoxing.activity;

/**
 * 描述：
 * 作者：徐小星 on 2016/8/26 0026 10:17
 * 邮箱：xx@yougudongli.com
 */
public class Test {
    public static void main(String args[]) {
        Info<String> i = new Info<String>();        // 使用String为泛型类型
        i.setVar("MLDN");                            // 设置内容
        fun(i);
    }

    public static void fun(Info<?> temp) {        // 可以接收任意的泛型对象
        System.out.println("内容：" + temp);
    }
}

class Info<T extends String> {    // 此处泛型只能是数字类型
    private T var;        // 定义泛型变量

    public void setVar(T var) {
        this.var = var;
    }

    public T getVar() {
        return this.var;
    }

    public String toString() {    // 直接打印
        return this.var.toString();
    }
};

