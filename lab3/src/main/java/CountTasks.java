public enum CountTasks {
    MIN(10),
    MIDDLE(20),
    MAX(100);

    CountTasks(int value) {
        this.value = value;
    }

    private final int value;

    public int getValue() {
        return value;
    }
}
