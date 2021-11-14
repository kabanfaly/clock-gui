package controller;

public class Main {

    private final ClockController clockController;

    public Main() {
        clockController = new ClockController(1000);
        clockController.timer.addTimerListener(new timer.IListenToTimer());
    }

    public static void main(String[] args) {
        new Main();
    }
}
