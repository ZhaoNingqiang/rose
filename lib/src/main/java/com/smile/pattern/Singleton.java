package com.smile.pattern;

/**
 * @Description: 单例模式的五种写法：
 * 1、懒汉
 * 2、恶汉
 * 3、静态内部类
 * 4、枚举
 * 5、双重校验锁
 * @Author: ZhaoNingqiang
 * @Time 2016/11/08 下午3:30
 */

public class Singleton {
    public static void main(String args[]) {
        EnumSingleton.INSTANCE.print("hello myfriend !");
    }

    /**
     * 1、双重校验锁
     * //详见：http://www.ibm.com/developerworks/cn/java/j-dcl.html
     */
    static class DoubleCheckLockSingleton {
        private static volatile DoubleCheckLockSingleton INSTANCE;

        private DoubleCheckLockSingleton() {
        }

        public static DoubleCheckLockSingleton getInstance() {
            if (INSTANCE == null) {
                synchronized (DoubleCheckLockSingleton.class) {
                    if (INSTANCE == null) {
                        INSTANCE = new DoubleCheckLockSingleton();
                    }
                }
            }
            return INSTANCE;
        }


    }

    /**
     * 2、、枚举，《Effective Java》作者推荐使用的方法，优点：不仅能避免多线程同步问题，而且还能防止反序列化重新创建新的对象
     */
    enum EnumSingleton {
        INSTANCE;

        public void print(String text) {
            System.out.println(text);
        }
    }

    /**
     * 3、静态内部类 优点：加载时不会初始化静态变量INSTANCE，因为没有主动使用，达到Lazy loading
     */
    static class StaticInnerClassSingleton {

        private StaticInnerClassSingleton() {
        }

        private static class SingletonHolder {
            public static StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
        }

        public static StaticInnerClassSingleton getInstance() {
            return SingletonHolder.INSTANCE;
        }
    }

    /**
     * 4、恶汉,缺点：没有达到lazy loading的效果
     */
    public static class HungrySingleton {
        private static HungrySingleton mInstance = new HungrySingleton();

        private HungrySingleton() {
        }

        ;

        public static HungrySingleton getInstance() {
            return mInstance;
        }
    }

    /**
     * 5、懒汉，常用的写法
     */
    public static class LazySingleton {
        private static LazySingleton INSTANCE;

        private LazySingleton() {
        }

        public static LazySingleton getInstance() {
            if (INSTANCE == null) {
                INSTANCE = new LazySingleton();
            }
            return INSTANCE;
        }
    }


}
