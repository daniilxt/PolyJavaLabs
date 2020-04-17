import java.util.concurrent.BlockingQueue;

public class StudentGenerator implements Runnable {
    private final BlockingQueue<Student> studentsQueue;
    private int labsCount;
    private Subjects typeOfSubject;

    public StudentGenerator(BlockingQueue<Student> studentsQueue) {
        this.studentsQueue = studentsQueue;
    }

    private void generateStudent() {
        int generatedLabsCount = (int) (Math.random() * 3);
        int generatedSubjectNum = (int) (Math.random() * 3);

        CountTasks typeOfLab = CountTasks.values()[generatedLabsCount];
        labsCount = typeOfLab.getValue();
        typeOfSubject = Subjects.values()[generatedSubjectNum];

    }

    @Override
    public void run() {
        try {
            while (true) {
                generateStudent();
                Student student = new Student(labsCount, typeOfSubject);
                studentsQueue.put(student);
                System.out.println(String.format("Created student with ID: %d with subject: %s and count of labs: %d  \n",student.getID() , typeOfSubject.toString(), labsCount));
            }
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}