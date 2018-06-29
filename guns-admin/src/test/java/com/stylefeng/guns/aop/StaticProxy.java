package com.stylefeng.guns.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class StaticProxy
{
    public static void main(String[] args) {
        Transaction transaction=new Transaction();
        PersonDaoImpl personDao=new PersonDaoImpl();
        MyInterceptor myInterceptor=new MyInterceptor();
        PersonDaoImpl cglibProxy = (PersonDaoImpl) myInterceptor.createCglibProxy(personDao, transaction);
        cglibProxy.savePerson();


    }
}
class PersonDaoImpl {
    public void savePerson() {
        System.out.println("save person");
    }
    public void updatePerson() {
        System.out.println("update person");
    }
}
class Transaction {
    public void startTran(){
        System.out.println("start tran");
    }
    public void endTran(){
        System.out.println("end tran");
    }
}
class MyInterceptor implements MethodInterceptor {
    private Transaction transaction;
    private Object target;
    public Object createCglibProxy(Object target,Transaction transaction){
        this.target=target;
        this.transaction=transaction;
        //生成代理对象
        Enhancer enhancer=new Enhancer();
        //设置父类
        enhancer.setSuperclass(this.target.getClass());
        //设置回调对象为本身
        enhancer.setCallback(this);
        return enhancer.create();//创建代理对象
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        transaction.startTran();
        method.invoke(this.target,objects);
        transaction.endTran();
        return null;
    }
}