package sbu.cs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    For this exercise, you must simulate a CPU with a single core.
    You will receive an arraylist of tasks as input. Each task has a processing
    time which is the time it needs to run in order to fully execute.

    The CPU must choose the task with the shortest processing time and create
    a new thread for it. The main thread should wait for the task to fully
    execute and then join with it, before starting the next task.

    Once a task is fully executed, **add its ID to the executed tasks arraylist.**
    Use the tests provided in the test folder to ensure your code works correctly.
 */

public class CPU_Simulator
{
    public static class Task implements Runnable {
        long processingTime;
        String ID;
        public Task(String ID, long processingTime) {
            this.ID = ID;
            this.processingTime  = processingTime;
        // TODO
        }

        /*
                Simulate running a task by utilizing** the sleep method for the duration of
                the task's processingTime**. The processing time is given in milliseconds.
            */

        @Override
        public void run() {
            try {
                Thread.sleep(processingTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // TODO
        }
    }

    /*
        The startProcessing function should be called at the start of your program.
        Here the CPU selects the next shortest task to run (also known as the
        shortest task first scheduling algorithm) and creates a thread for it to run.
    */
    public ArrayList<String> startSimulation(ArrayList<Task> tasks) throws InterruptedException {
        ArrayList<String> executedTasks = new ArrayList<>();
        int takssize = tasks.size();
            for( int i = 0 ; i < takssize ; i++) {
                Task shortest = tasks.get(0) ;

                for (Task b : tasks) {
                    if (b.processingTime < shortest.processingTime) {
                        shortest = b;// compair or add
                    }
                }

                Thread sleepthread = new Thread(shortest);
                sleepthread.start();
                executedTasks.add(shortest.ID);

                for (int f = 0; f < takssize; f++){
                        if (shortest.processingTime ==tasks.get(f).processingTime){
                            tasks.remove(f);
                            break;
                    }
                }

            }
            // TODO
        return executedTasks;
    }

    public static void main(String[] args) {

    }
}




