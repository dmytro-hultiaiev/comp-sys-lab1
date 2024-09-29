import java.util.List;

public class Task {
    private int countOfOperations;
    private List<Integer> processors;

    public Task(int countOfOperations, List<Integer> CPUs) {
        this.countOfOperations = countOfOperations;
        this.processors = CPUs;
    }

    public int getCountOfOperations() {
        return countOfOperations;
    }

    public List<Integer> getProcessors() {
        return processors;
    }
}
