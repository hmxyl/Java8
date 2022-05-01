package example;

public class ThreadTest {

    public static void main(String[] args) {
        readFromDb();
        readFromIo();
    }

    private static void readFromDb() {
        Thread t2 = new Thread("readFromDb") {
            @Override
            public void run() {
                try {
                    System.out.println(">> Begin read from db");
                    Thread.sleep(1000 * 120);
                    System.out.println(">> End read from db and handle it");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(">> db handle successfully");
            }
        };
        t2.start();
    }

    private static void readFromIo() {
        Thread t1 = new Thread("readFromIo") {
            @Override
            public void run() {
                try {
                    System.out.println(">> Begin read from io");
                    Thread.sleep(1000 * 120);
                    System.out.println(">> End read from io  and handle it");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(">> io handle successfully");
            }
        };
        t1.start();
    }
}
