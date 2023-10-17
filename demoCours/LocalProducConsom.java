package demoCours;

import java.util.*;

public class LocalProducConsom {
    private static volatile int noProducts = 0;
    private static final int BUFFER_SIZE = 5;
    private static final Queue<Integer> buffer = new LinkedList<>();

    public static void main(String[] args) {
        int halfNbProcesses = 20;
        List<Thread> processes = new ArrayList<>(2*halfNbProcesses);
        boolean[] go = {true};
        for (int i = 0; i < halfNbProcesses; i++) {
            final int j = i;
            processes.add(Thread.ofVirtual().start(() -> {
                while (go[0]) { produce("p" + j); Thread.yield(); }
            }));
        }

        for (int i = 0; i < halfNbProcesses; i++) {
            final var j = i;
            processes.add(Thread.ofVirtual().start(() -> {
                while (go[0]) { consume("c" + j); Thread.yield(); }
            }));
        }

        try { Thread.sleep(5000); } catch (InterruptedException e) { throw new RuntimeException(e);}
        go[0] = false;
        try { Thread.sleep(10); } catch (InterruptedException e) { throw new RuntimeException(e);}
        System.err.println("-".repeat(30));
        System.err.println("Nb products created = " + noProducts);
        synchronized (buffer) {
            System.err.println(Arrays.toString(buffer.toArray()));
        }
        System.exit(0);
    }

    private static void produce(String name) {
        synchronized (buffer) {
            while (buffer.size() >= BUFFER_SIZE) {
                try { buffer.wait(); } catch (InterruptedException ingnoredException) {}
            }

            buffer.add(noProducts);
            //System.out.println(name + " produced: " + noProducts);
            noProducts++;
            buffer.notifyAll();
        }
    }

    private static void consume(String name) {
        synchronized (buffer) {
            while (buffer.isEmpty()) {
                try { buffer.wait(); } catch (InterruptedException ingnoredException) {}
            }

            int item = buffer.poll();
            //System.out.println(name + " consumed: " + item);
            buffer.notifyAll();
        }
    }
}