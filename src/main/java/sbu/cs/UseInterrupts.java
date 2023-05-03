package sbu.cs;

/*
    In this exercise, you must analyse the following code and use interrupts
    in the main function to terminate threads that run for longer than 3 seconds.

    A thread may run for longer than 3 seconds due the many different reasons,
    including lengthy process times or getting stuck in an infinite loop.

    Take note that you are NOT ALLOWED to change or delete any existing line of code.
 */

import java.time.Duration;
import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;

public class UseInterrupts
{
    /*
        TODO
         Analyse the following class and add new code where necessary.
         If an object from this type of thread is Interrupted, it must print this:
            "{ThreadName} has been interrupted"
         And then terminate itself.
     */
    public static class SleepThread extends Thread {
        int sleepCounter;

        public SleepThread(int sleepCounter) {
            super();
            this.sleepCounter = sleepCounter;
        }

        @Override
        public void run() {
            System.out.println(this.getName() + " is Active.");

            while (this.sleepCounter > 0)
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println( Thread.currentThread().getName() + " has been interrupted\"");
                    Thread.currentThread().stop();

                }
                finally {
                    this.sleepCounter--;
                    System.out.println("Number of sleeps remaining: " + this.sleepCounter);
                }
            }

        }
    }

    /*
        TODO
         Analyse the following class and add new code where necessary.
         If an object from this type of thread is Interrupted, it must print this:
            "{ThreadName} has been interrupted"
         And then terminate itself.
         (Hint: Use the isInterrupted() method)
     */
    public static class LoopThread extends Thread {
        int value;
        public LoopThread(int value) {
            super();
            this.value = value;
        }

        @Override
        public void run() {
            System.out.println(this.getName() + " is Active.");

            for (int i = 0; i < 10; i += 3)
            {
                i -= this.value;
                if(isInterrupted()){
                    System.out.println(Thread.currentThread().getName() + " has been interrupted\"");
                    Thread.currentThread().stop();
                }
            }
        }
    }

    /*
        You can add new code to the main function. This is where you must utilize interrupts.
        No existing line of code should be changed or deleted.
     */
    public static void main(String[] args) throws InterruptedException {
        SleepThread sleepThread = new SleepThread(5);
        sleepThread.start();

        Timer timer = new Timer();
        TimeOutTask timeOutTask = new TimeOutTask(sleepThread, timer);
        timer.schedule(timeOutTask, 3000);

        // TODO  Check if this thread runs for longer than 3 seconds (if it does, interrupt it)

        LoopThread loopThread = new LoopThread(3);
        loopThread.start();

        Timer timer2 = new Timer();
        TimeOutTask timeOutTask2 = new TimeOutTask(loopThread, timer2);
        timer2.schedule(timeOutTask2, 3000);
        // TODO  Check if this thread runs for longer than 3 seconds (if it does, interrupt it)

    }
    static class TimeOutTask extends TimerTask {
        private final Thread thread;
        private final Timer timer;

        public TimeOutTask(Thread thread, Timer timer) {
            this.thread = thread;
            this.timer = timer;
        }

        @Override
        public void run() {
            if (thread.isAlive()) {
                thread.interrupt();
                timer.cancel();
            }
        }
    }
}
