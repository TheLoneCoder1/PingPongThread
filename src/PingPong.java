import java.util.Random;

public class PingPong {

    private volatile boolean isPing = true;

    public void play() throws InterruptedException {
        new Thread(()->
        {
            while (true)
            {
                if (isPing) {
                    synchronized (this) {
                        System.out.println("Ping");
                        isPing = false;
                    }
                }
                try {
                    Thread.sleep(new Random().nextInt(1600)+400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->
            {
            while (true)
            {
                if (!isPing) {
                    synchronized (this) {
                        System.out.println("Pong");
                        isPing = true;
                    }
                }
                try {
                    Thread.sleep(new Random().nextInt(1600)+400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) throws InterruptedException {
        PingPong pingPong = new PingPong();
        pingPong.play();
    }
}
