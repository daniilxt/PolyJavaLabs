import java.util.concurrent.BlockingQueue;

import static java.lang.Thread.sleep;

public class Robot implements Runnable {
  private final BlockingQueue<Student> studentsQueue;
  private final Subject handleType;
  private static final int DELAY = 100;

  public Robot(String handleType, BlockingQueue<Student> studentsQueue) {
    this.handleType = Subject.valueOf(handleType);
    this.studentsQueue = studentsQueue;
  }

  private void handle() {
    try {
      Student student = studentsQueue.peek();
      if (student != null) {
        if (student.getSubject() == handleType) {
          student = studentsQueue.take();
          System.out.println(String.format("I Robot : %s i catch student with ID :%d and handle his\n", handleType.toString(), student.getID()));

          int countForHandle = student.getLabsCount();
          sleep(DELAY * countForHandle / 5);
          System.out.println(String.format("I Robot : %s i finished checking with ID : %d\n", handleType.toString(), student.getID()));
        }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Override
  public void run() {
    while (true) {
      handle();
    }
  }
}
