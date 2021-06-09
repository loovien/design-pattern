package io.github.loovien;

/**
 * created 6/4/2021 10:31 PM
 *
 * @author luowen <loovien@163.com>
 */
public class Strategy {

    @FunctionalInterface
    public interface Engine {
        String dl();
    }

    public static void download(Engine engine) {
        System.out.println("download starting");
        System.out.println("use engine: " + engine.dl());
        System.out.println("download completed");
    }

    public static void main(String[] args) {
        download(() -> "baidu");
        download(() -> "google");
        download(() -> "bing");
    }

}
