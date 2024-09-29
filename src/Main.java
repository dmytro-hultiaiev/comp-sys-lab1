public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();

        controller.createProcessors();
        controller.setTimeLimitForTask();
        controller.setProbability();

        System.out.println("FIFO:");
        controller.FIFO();

        System.out.println("\nWeak processor:");
        controller.weakProcessorScheduler();

        System.out.println("\nStrong processor:");
        controller.strongProcessorScheduler(20,4);

        System.out.println("\nStrong processor custom:");
        controller.strongProcessorScheduler(30,4);
    }
}