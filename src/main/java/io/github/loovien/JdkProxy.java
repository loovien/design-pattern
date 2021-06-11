package io.github.loovien;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * created 6/11/2021 11:24 PM
 *
 * @author luowen <loovien@163.com>
 */
public class JdkProxy {
    interface Animals {
        void eat();
    }

    public static class Dog implements Animals {

        @Override
        public void eat() {
            System.out.println("dog like eat meat!!");
        }
    }

    public static void main(String[] args) {
        // jdk.proxy.ProxyGenerator.saveGeneratedFiles
        System.setProperty("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        Animals animals = (Animals) Proxy.newProxyInstance(Dog.class.getClassLoader(), new Class[]{Animals.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                System.out.println("proxy ============> ");

                Object result = method.invoke(new Dog(), args);

                System.out.println("proxy <============ ");
                return result;

            }
        });
        animals.eat();
    }
}
