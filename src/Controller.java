import java.util.*;

public class Controller {
    private int amountOfProcessors = 5;
    private List<Processor> processors;
    private int minTaskTime;
    private int maxTaskTime;
    private int taskProbability;
    private int taskAmount = 10000;

    public void createProcessors(){
        processors = new ArrayList<>();
        for(int i = 0; i < amountOfProcessors; i++){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter power amount for processor with id " + i);
            int powerOfProcessor = scanner.nextInt();
            processors.add(new Processor(i,powerOfProcessor));
        }
    }

    public void setTimeLimitForTask(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter min time for task: ");
        minTaskTime = scanner.nextInt();
        System.out.println("Enter max time for task: ");
        maxTaskTime = scanner.nextInt();
    }

    public void setProbability(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new probability (from 1 to 99): ");
        taskProbability = scanner.nextInt();
    }

    public List<Task> generateTasks(){
        List<Task> tasks = new ArrayList<>();
        int minProcessorPower = processors.stream()
                .map(Processor::getPower)
                .min(Comparator.naturalOrder())
                .orElseThrow(() -> new RuntimeException("ERROR to get min processor power"));
        int minTaskComplexity = minTaskTime * minProcessorPower;
        int maxTaskComplexity = maxTaskTime * minProcessorPower;
        ListOfProcessors listOfProcessors = new ListOfProcessors();
        int sizeOfListOfProcessor = listOfProcessors.getList().size();

        for(int i = 0; i < taskAmount; i++){
            int countOfOperations = new Random().nextInt(maxTaskComplexity - minTaskComplexity + 1) + minTaskComplexity;
            List<Integer> processors = listOfProcessors.getList().get(new Random().nextInt(sizeOfListOfProcessor));
            tasks.add(new Task(countOfOperations, processors));
        }

        return tasks;
    }

    public int searchProcessorForTask(Task task){
        for(int i = 0; i < amountOfProcessors; i++){
            if(processors.get(i).isFree() && task.getProcessors().contains(i)){
                return i;
            }
        }
        return -1;
    }

    public boolean isProcessorFree() {
        for (int i = 0; i < amountOfProcessors; i++) {
            Processor processor = processors.get(i);
                if (!processor.isFree()) {
                    return false;
                }
            }
        return true;
    }

    public Task searchTaskForProcessor(Processor processor, List<Task> tasks){
        if(!tasks.isEmpty()){
            for(int i = 0; i < tasks.size(); i++){
                if(tasks.get(i).getProcessors().contains(processor.getIndex())){
                    return tasks.get(i);
                }
            }
        }
        return null;
    }

    public void resultAnalyzer(int runTime, int n){
        for(int i = n; i < amountOfProcessors; i++){
            Processor processor = processors.get(i);
            System.out.println("Processor with ID " + i + " worked " + processor.getTimeWork() + " and slept " + processor.getTimeSleep());
        }

        int countOfOperations = 0;
        for(int i = n; i < amountOfProcessors; i++){
            long countTime = (10000L * processors.get(i).getTimeWork()) / runTime;
            countOfOperations += (int) (processors.get(i).getPower() * countTime);
        }

        int theoreticalOperationsValue = processors.stream()
                .filter(p -> p.getIndex() >= n)
                .map(Processor::getPower)
                .mapToInt(power -> power * 10000)
                .sum();

        System.out.println("Count of operations in 10 seconds: " + countOfOperations);
        System.out.println("Theoretical operations value in 10 seconds: " + theoreticalOperationsValue);
        System.out.println("Efficiency: " + ((float)countOfOperations / (float)theoreticalOperationsValue * 100) + " %");
    }

    public void FIFO(){
        List<Task> tasks = generateTasks();
        int runTime = 0;

        for(Processor processor : processors){
            processor.resetProcessor();
        }

        int taskIndex = 0;

        while(true){
            if(new Random().nextInt(100) < taskProbability){
                if(taskIndex < tasks.size()){
                    Task task = tasks.get(taskIndex);
                    int processorID = searchProcessorForTask(task);
                    if (processorID > -1) {
                        processors.get(processorID).setFree(false);
                        processors.get(processorID).setTimeWorkTask(task.getCountOfOperations() / processors.get(processorID).getPower());
                        taskIndex++;
                    }
                }
            }

            for (Processor processor : processors) {
                processor.run();
            }
            runTime++;

            if(taskIndex >= tasks.size()){
                boolean checkIsProcessorFree = isProcessorFree();
                if(checkIsProcessorFree){
                    break;
                }
            }
        }
        resultAnalyzer(runTime, 0);
    }

    public void weakProcessorScheduler(){
        List<Task> tasks = generateTasks();
        int runTime = 0;

        for(Processor processor : processors){
            processor.resetProcessor();
        }

        while(true){
            for(int i = 1; i < amountOfProcessors; i++){
                Processor processor = processors.get(i);
                if(processor.isFree()){
                    Task task = searchTaskForProcessor(processor, tasks);
                    if(task != null){
                        processor.setFree(false);
                        processor.setTimeWorkTask(task.getCountOfOperations() / processor.getPower());
                        tasks.remove(task);
                    }
                }
            }

            for (Processor processor : processors) {
                processor.run();
            }
            runTime++;

            if(tasks.isEmpty()){
                boolean checkIsProcessorFree = isProcessorFree();
                if(checkIsProcessorFree){
                    break;
                }
            }
        }

        resultAnalyzer(runTime, 1);
    }

    public void strongProcessorScheduler(int workTime, int planTime){
        List<Task> tasks = generateTasks();
        int runTime = 0;
        int timeWorkAndSleepProcPlan = 0;

        for(Processor processor : processors){
            processor.resetProcessor();
        }

        while(true){
            for(int i = 0; i < amountOfProcessors; i++){
                Processor processor = processors.get(i);
                if(processor.isFree()){
                    Task task = searchTaskForProcessor(processor, tasks);
                    if(task != null){
                        processor.setFree(false);
                        processor.setTimeWorkTask(task.getCountOfOperations() / processor.getPower());
                        tasks.remove(task);
                    }
                }
            }

            for (Processor processor : processors) {
                processor.run();
            }
            runTime++;

            if (timeWorkAndSleepProcPlan <= workTime) {
                timeWorkAndSleepProcPlan++;
            }
            else if (timeWorkAndSleepProcPlan <= workTime + planTime) {
                processors.get(4).setFree(false);
                timeWorkAndSleepProcPlan++;
            }
            else {
                processors.get(4).setFree(true);
                timeWorkAndSleepProcPlan = 0;
            }

            if(tasks.isEmpty()){
                boolean checkIsProcessorFree = isProcessorFree();
                if(checkIsProcessorFree){
                    break;
                }
            }
        }

        resultAnalyzer(runTime, 0);
    }
}
