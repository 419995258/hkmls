package cn.xdf.hkmls.util.context;

/**
 * @author daiyafei3@xdf.cn
 * @desc 操作当前线程中的值
 * @classname LocalHolder
 * @date 2020/9/1
 */
public class LocalHolder {

    private static final ThreadLocal<LocalContext> threadLocal = new ThreadLocal<>();

    public static void add(LocalContext local) {
        threadLocal.set(local);
    }

    public static LocalContext get() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
