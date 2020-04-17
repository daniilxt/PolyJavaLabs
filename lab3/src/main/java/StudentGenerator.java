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
/*        while (true) {
            locker.lock();

            try {
                while (studentsQueue.remainingCapacity() == 0) {
                    condition.await();
                }

                studentsQueue.add(new Student(number[(int) (Math.random() * 3) + 1], subject[(int) (Math.random() * 3) + 1]));

                condition.signalAll();
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            } finally {
                locker.unlock();
            }
        }
        try {
            studentsQueue.put(new Student(labsCount, typeOfSubject));
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }*/

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