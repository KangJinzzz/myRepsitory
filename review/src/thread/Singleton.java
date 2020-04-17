package thread;

public class Singleton {
    private volatile static Singleton instance = null;
    //构造函数私有，防止被实例化
    private Singleton(){}

    public static Singleton getInstance() {
        //一重校验
        if (instance == null) {
            synchronized (Singleton.class) {
                //二重校验
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
