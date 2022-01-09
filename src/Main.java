
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(1);

        /*
        executor.submit(new Runnable() {
            @Override
            public void run() { // run is void and doesn't return value !
                Random random = new Random();

                System.out.println("Thread is working....");

                int sure =  random.nextInt(1000) + 2000;

                try {
                    Thread.sleep(time);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Thread exits ....");

            }
        });
        */

        // Future and Callable provide to return value .
        Future<?> future  = executor.submit(new Callable<Integer>(){
            @Override
            public Integer call() throws Exception { // run() doesn't value so call() is being used !

                Random random = new Random();

                System.out.println("Thread is working ....");

                int time =  random.nextInt(1000) + 2000; // time value

                //Intereption exception will see when time is big than 2500
                /*if (sure > 2500){
                    throw new IOException(" Thread slept too long  ....");

                }*/


                try {
                    Thread.sleep(time);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Thread exits....");

                return time; // return time !

            }
        });


        executor.shutdown();

        try {
            System.out.println("Return value  : " + future.get());  // with future get the time value
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            System.out.println(ex);
        }
    }
}
