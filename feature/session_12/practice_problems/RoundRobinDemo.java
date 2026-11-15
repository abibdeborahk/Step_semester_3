
import java.util.*;

class Job {

    String name;
    int remainingTime;
    int finishTime;

    Job(String name, int time) {
        this.name = name;
        this.remainingTime = time;
    }
}

class LabScheduler {

    Queue<Job> queue = new LinkedList<>();

    int quantum;
    int currentTime = 0;

    LabScheduler(int quantum) {
        this.quantum = quantum;
    }

    void addJob(String name, int time) {
        queue.add(new Job(name, time));
    }

    void schedule() {

        List<String> execution = new ArrayList<>();

        List<Job> finished = new ArrayList<>();

        while (!queue.isEmpty()) {

            Job job = queue.poll();

            int start = currentTime;

            int runTime = Math.min(quantum, job.remainingTime);

            currentTime += runTime;

            job.remainingTime -= runTime;

            execution.add(
                job.name + "(" + start + "-" + currentTime + ")"
            );

            if (job.remainingTime > 0) {
                queue.add(job);
            }

            else {
                job.finishTime = currentTime;
                finished.add(job);
            }
        }

        // Print execution sequence
        for (String s : execution) {
            System.out.print(s + " ");
        }

        System.out.println();

        // Print finishing times
        for (Job j : finished) {
            System.out.println(
                "finish " + j.name + " " + j.finishTime
            );
        }
    }
}

public class RoundRobinDemo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int quantum = sc.nextInt();

        int n = sc.nextInt();

        LabScheduler scheduler = new LabScheduler(quantum);

        for (int i = 0; i < n; i++) {

            String name = sc.next();
            int time = sc.nextInt();

            scheduler.addJob(name, time);
        }

        scheduler.schedule();

        sc.close();
    }
}
