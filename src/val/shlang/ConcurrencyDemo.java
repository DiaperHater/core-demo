package val.shlang;

import java.util.concurrent.*;

public class ConcurrencyDemo extends Demo {

    @Override
    protected void demo() {
        threadDemo();
        synchronizedDemo();
        singleExecutorAndFutureDemo();
        shutdownNowDemo();
    }

    private void shutdownNowDemo() {
        //shutdownNow() demo
        ExecutorService executor = null;

        try{
            executor = Executors.newSingleThreadExecutor();
            executor.submit(() -> {
                System.out.println("Start");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
                System.out.println("Finish");
            });
        } finally {
            if (executor != null) executor.shutdownNow();
        }
    }

    private void singleExecutorAndFutureDemo() {
        //single executor get result from future;
        ExecutorService singleExecutor = null;
        Future result = null;
        try {
            singleExecutor = Executors.newSingleThreadExecutor();
            result = singleExecutor.submit(()->{
                try {
                    System.out.println("Begin Callable: " + Thread.currentThread().getName());
                    System.out.printf("Callable: %s is going to to sleep for 5 seconds\r\n",
                            Thread.currentThread().getName());
                    Thread.sleep(5000);
                    System.out.println("End of Callable: " + Thread.currentThread().getName());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return "Future result! ha-ha!!!";
            });

        } finally {
            if (singleExecutor!=null){singleExecutor.shutdown();}
        }

        if (result != null){
            try {
                System.out.println(result.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    private void synchronizedDemo() {
        class PiggyBank{
            long balance = 0;
            synchronized void increment(){setBalance(getBalance() + 1);}

            private long getBalance() {
                return balance;
            }

            private void setBalance(long balance) {
                this.balance = balance;
            }
        }
        PiggyBank pb = new PiggyBank();

        ExecutorService executor = Executors.newFixedThreadPool(5);
        Runnable work = new Runnable() {
            @Override
            public void run() {
                    for (int j = 0; j < 1_000_000; j++) {
                        if(Thread.interrupted()){
                            break;
                        }
                        pb.increment();
                    }
                System.out.println(Thread.currentThread().getName() + " Done!");
            }
        };

        for (int i = 0; i < 5; i++) {
            executor.execute(work);
        }
        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("PiggyBank balance: %d$\r\n", pb.balance);

    }

    private void threadDemo() {
        Runnable worker = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };

        Thread thread = new Thread(worker);
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Demo d = new ConcurrencyDemo();
        d.run();
    }
}
