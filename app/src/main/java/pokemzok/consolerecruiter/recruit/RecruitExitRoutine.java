package pokemzok.consolerecruiter.recruit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pokemzok.consolerecruiter.domain.*;
import pokemzok.consolerecruiter.recruit.LenientCountingStrategy;
import pokemzok.consolerecruiter.recruit.UniversityCountingStrategy;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Slf4j
public class RecruitExitRoutine implements Runnable {

    private final static long RATING_TIMEOUT = 2;

    private final Recruit recruit;
    private final RecruitRepository recruitRepository;
    private final List<CountingPointsStrategy> strategies;

    @Override
    public void run() {
        recruitResults();
        System.exit(0);
    }

    public void recruitResults() {
        log.info("The test is finished " + recruit.getName() + ".");
        recruit.stopAnsweringQuestion();
        rateRecruit();
        recruit.recruitAnswerCollection().printAnswers();
        recruit.rateCollection().printRates();
        recruitRepository.save(recruit);
    }

    private void rateRecruit() {

        ExecutorService executorService = Executors.newFixedThreadPool(strategies.size());
        strategies.forEach(strategy ->
                executorService.submit(
                        () -> recruit.rate(strategy)
                )
        );
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(RATING_TIMEOUT, TimeUnit.MINUTES)) {
                executorService.shutdownNow();
                throw new RuntimeException("Thread was terminated after" + RATING_TIMEOUT + "minutes of waiting");
            }
        } catch (InterruptedException ex) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
            throw new RuntimeException(ex);
        }
    }
}
