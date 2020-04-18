
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) {

        BlockingQueue<Student> studentsQueue = new ArrayBlockingQueue<>(10);
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

        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();
    }
}