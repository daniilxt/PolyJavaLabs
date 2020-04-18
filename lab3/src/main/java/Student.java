public class Student {
  private final int labsCount;
  private final Subject subject;
  private static int countID = 1;
  private final int id;

  public Student(int labsCount, Subject subject) {
    if (subject == null) {
      throw new IllegalArgumentException("Can't create student with null");
    }
    if (!((labsCount == 10) || (labsCount == 20) || (labsCount == 100))) {
      throw new IllegalArgumentException("Incorrect labs count:" + labsCount);
    }

    if ((subject != Subject.MATH) && (subject != Subject.OOP) && (subject != Subject.PHYSICS)) {
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

  public Subject getSubject() {
    return subject;
  }

  public int getID() {
    return id;
  }
}
