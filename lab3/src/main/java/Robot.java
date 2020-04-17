import java.util.concurrent.BlockingQueue;

import static java.lang.Thread.sleep;

public class Robot implements Runnable {
    private final BlockingQueue<Student> studentsQueue;
    private final Subjects handleType;
    private static final int DELAY = 100;

    public Robot(String handleType, BlockingQueue<Student> studentsQueue) {
        this.handleType = Subjects.valueOf(handleType);
        this.studentsQueue = studentsQueue;
    }

    private void handle() throws InterruptedException {
        if (!studentsQueue.isEmpty() && (studentsQueue.peek() != null)) {
            if (studentsQueue.peek().getSubject() == handleType) {
                Student student = studentsQueue.take();
                System.out.println(String.format("I Robot : %s i catch student with ID :%d and handle his\n", handleType.toString(), student.getID()));

                int countForHandle = student.getLabsCount();
                sleep(DELAY * countForHandle / 5);
                System.out.println(String.format("I Robot : %s i finished checking with ID : %d\n", handleType.toString(), student.getID()));
            }

        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                handle();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
