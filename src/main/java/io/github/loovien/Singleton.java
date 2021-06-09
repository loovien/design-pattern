package io.github.loovien;

/**
 * created 6/3/2021 10:41 PM
 * <p>
 * 主要用于整个应用就需要1个对像就可以了。 比如各在框架的 context, DI 都是单例模式。
 *
 * @author luowen <loovien@163.com>
 */
public class Singleton {

    /**
     * 懒汉式
     */
    public final static class PositiveSingleton {
        public final static PositiveSingleton GLOBAL = new PositiveSingleton();

        private PositiveSingleton() {
        }
    }

    /**
     * 饿汉式 DLC
     */
    public final static class LazySingleton {

        private LazySingleton() {
        }

        private static LazySingleton lazySingleton = null;

        public static LazySingleton getInstance() {
            if (lazySingleton == null) {
                synchronized (LazySingleton.class) {
                    if (lazySingleton != null) {
                        return lazySingleton;
                    }
                    lazySingleton = new LazySingleton();
                }
            }
            return lazySingleton;
        }

    }

    public static class InnerSingleton {
        private InnerSingleton() {
        }

        private static class InnerClazz {
            private final static InnerSingleton GLOBAL = new InnerSingleton();
        }

        public static InnerSingleton getInstance() {
            return InnerClazz.GLOBAL;
        }

    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                LazySingleton instance = LazySingleton.getInstance();
                LazySingleton instance1 = LazySingleton.getInstance();
                System.out.println(instance.equals(instance1));
            }).start();
        }

    }

    /**
     * 枚举单例
     */
    public enum SingletonEnum {

        INSTANCE("im a singleton");

        private String message;

        SingletonEnum(String s) {
            this.message = s;
        }

        public String getMessage() {
            return message;
        }
    }

}
