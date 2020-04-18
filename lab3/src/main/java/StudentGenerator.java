import java.util.concurrent.BlockingQueue;

public class StudentGenerator implements Runnable {
  private final BlockingQueue<Student> studentsQueue;
  private int labsCount;
  private Subject typeOfSubject;

  public StudentGenerator(BlockingQueue<Student> studentsQueue) {
    this.studentsQueue = studentsQueue;
  }

  private void generateStudent() {
    int generatedLabsCount = (int) (Math.random() * 3);
    int generatedSubjectNum = (int) (Math.random() * 3);

    // получаем тип из ENUM
    CountTasks typeOfLab = CountTasks.values()[generatedLabsCount];
    labsCount = typeOfLab.getValue();
    typeOfSubject = Subject.values()[generatedSubjectNum];

  }

  @Override
  public void run() {
    try {
      while (true) {
        generateStudent();
        Student student = new Student(labsCount, typeOfSubject);
        studentsQueue.put(student);
        System.out.println(String.format("Created student with ID: %d with subject: %s and count of labs: %d  \n", student.getID(), typeOfSubject.toString(), labsCount));
      }
    } catch (InterruptedException ex) {
        ex.printStackTrace();
    }
  }
}