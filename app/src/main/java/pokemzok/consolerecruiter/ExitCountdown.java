package pokemzok.consolerecruiter;

import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RequiredArgsConstructor
class ExitCountdown {

    private final long minutesToExit;
    private final Runnable exitRoutine;
    private Timer timer;

    void start() {
        if (timer == null) {
            TimerTask exitTask = new TimerTask() {
                @Override
                public void run() {
                    ExecutorService executorService = Executors.newSingleThreadExecutor();
                    executorService.execute(exitRoutine);
                    executorService.shutdown();
                }
            };
            timer = new Timer();
            timer.schedule(exitTask, new Date(System.currentTimeMillis() + getMillisTillExit()));
        }
    }

    void stop() {
        timer.cancel();
    }

    private long getMillisTillExit() {
        return minutesToExit * 60 * 1000;
    }
}
