public class Student {
    private final int labsCount;
    private final Subjects subject;
    private static int countID = 1;
    private final int id;

    public Student(int labsCount, Subjects subject) {
        if (!((labsCount == 10) || (labsCount == 20) || (labsCount == 100))) {
            throw new IllegalArgumentException("Incorrect labs count:" + labsCount);
        }

        if ((subject != Subjects.MATH) && (subject != Subjects.OOP) && (subject != Subjects.PHYSICS)) {
            throw new IllegalArgumentException("Incorrect subject name");
        }
        this.labsCount = labsCount;
        this.subject = subject;
        this.id = countID;
        countID++;
    }

    public int getLabsCount() {
        return labsCount;
    }

    public Subjects getSubject() {
        return subject;
    }

    public int getID() {
        return id;
    }
}
