package com.stylefeng.guns.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *    1、代理对象调用方法是进行拦截器的invoke方法
 *    2、拦截器invoke方法中的method参数，是代理对象的方法名称
 *       代理对象调用方法是，实参传递给拦截器的invoke方法
 *    3、代理对象的方法体，就是拦截器中invoke方法中的内容
 *    优点：
 *    1、动态产生代理对象，只需要一个拦截器
 *    缺点：
 *    1、在invoke方法中做事务判断很复杂
 *    2、程序员还是写了拦截器，so invoke还需要修改
 */
public class StaticProxy
{
    public static void main(String[] args) {
        PersonDao target=new PersonDaoImpl();
        Transaction transaction=new Transaction();
        MyInterceptor myInterceptor=new MyInterceptor(target,transaction);
        /**
         * 第一个参数：目标类加载器
         * 第二个参数：目标对象实现所有接口
         * 第三个参数：拦截器
         */
        PersonDao proxy = (PersonDao) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                myInterceptor);
        proxy.savePerson();
        proxy.updatePerson();
    }
}
//persondao接口
interface PersonDao{
    public void savePerson();
    public void updatePerson();
}
class PersonDaoImpl implements  PersonDao{
    @Override
    public void savePerson() {
        System.out.println("save person");
    }
    @Override
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
class MyInterceptor implements InvocationHandler{
    private Transaction transaction;
    private Object target;
    public  MyInterceptor(Object target,Transaction transaction){
        this.target=target;
        this.transaction=transaction;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        this.transaction.startTran();
        method.invoke(this.target,args);//调用目标类的目标方法
        this.transaction.endTran();
        return null;
    }
}