import java.util.Random;

public class PingPong {

    private volatile boolean isPing = true;

    public class PingPongThread implements Runnable{

        String hit;

        public PingPongThread(String hit){
            this.hit = hit;
        }

        public void run(){
            while (true)
            {
                if (isPing  && hit.equals("Ping") ||
                   !isPing  && hit.equals("Pong")) {
                    synchronized (this) {
                        System.out.println(hit);
                        isPing = !isPing;
                    }
                }
                try {
                    Thread.sleep(new Random().nextInt(1600)+400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public void play() throws InterruptedException {
        PingPongThread pingThread = new PingPongThread("Ping");
        PingPongThread pongThread = new PingPongThread("Pong");
        new Thread(pingThread).start();
        new Thread(pongThread).start();
    }

    public static void main(String[] args) throws InterruptedException {
        PingPong pingPong = new PingPong();
        pingPong.play();
    }
}
