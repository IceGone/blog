package com.icegone.spring.boot.blog.test;

/**
 * @author Icegone
 * @date 2019-7-17
 */
public class AB {
    AB(){
        System.out.println("构造器：父类");
    }
    static{
        System.out.println("静态代码块：父类");
    }
    {
        System.out.println("代码块：父类");
    }
}

class B extends AB{
    B(){
        System.out.println("构造器：子类");
    }
    static{
        System.out.println("静态代码块：子类");
    }
    {
        System.out.println("代码块：子类");
    }
    public static void main(String []args){
        new B();
        new B();
    }
}
