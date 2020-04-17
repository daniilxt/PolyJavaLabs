/*
import java.security.PublicKey;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class Simulator {
    private int endTime = 5;
    private int capacity = 5;

    public int generate(int capacity, int endTime) {
        this.capacity = capacity;
        this.endTime = endTime;
        BlockingQueue<Student> studentsQueue = new ArrayBlockingQueue<>(capacity);
        StudentGenerator generator = new StudentGenerator(studentsQueue);

        Robot robotOOP = new Robot("MATH", studentsQueue);
        Robot robotMATH = new Robot("OOP", studentsQueue);
        Robot robotPHYSICS = new Robot("PHYSICS", studentsQueue);

        Thread thread0 = new Thread(generator);
        thread0.setName("GENERATOR");
        Thread thread1 = new Thread(robotMATH);
        thread1.setName("MATH");
        Thread thread2 = new Thread(robotOOP);
        thread2.setName("OOP");
        Thread thread3 = new Thread(robotPHYSICS);
        thread3.setName("PHYSICS");

        thread1.start();
        thread2.start();
        thread3.start();
        thread0.start();

        return 1;
    }
}
*/
