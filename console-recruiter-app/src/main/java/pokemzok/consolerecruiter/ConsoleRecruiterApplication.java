package pokemzok.consolerecruiter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.lang.NonNull;
import pokemzok.consolerecruiter.config.RatingStrategiesConfiguration;
import pokemzok.consolerecruiter.domain.CountingPointsStrategy;
import pokemzok.consolerecruiter.domain.RecruitRepository;
import pokemzok.consolerecruiter.domain.RecruitFactory;
import pokemzok.consolerecruiter.recruit.RecruitExitRoutine;

import java.util.List;

@SpringBootApplication
@Slf4j
@Import(RatingStrategiesConfiguration.class)
@EnableMongoRepositories(basePackages = "pokemzok.consolerecruiter")
public class ConsoleRecruiterApplication implements CommandLineRunner {


    private long timeLeft;
    private final RecruitFactory recruitFactory;
    private final RecruitRepository recruitRepository;
    @NonNull
    private final List<CountingPointsStrategy> strategies;

    public ConsoleRecruiterApplication(@Value("${time.left}") long timeLeft,
                                       RecruitFactory recruitFactory,
                                       RecruitRepository recruitRepository,
                                       List<CountingPointsStrategy> strategies
    ) {
        this.recruitFactory = recruitFactory;
        this.recruitRepository = recruitRepository;
        this.timeLeft = timeLeft;
        this.strategies = strategies;
        new StrategiesPrecondition().check(strategies);
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsoleRecruiterApplication.class, args);
    }

    @Override
    public void run(String... args) {
        var recruit = recruitFactory.create();
        var exitRoutine = new RecruitExitRoutine(recruit, recruitRepository, strategies);
        var countdown = new ExitCountdown(timeLeft, exitRoutine);
        countdown.start();
        log.info("Starting {} minute countdown before application exits", timeLeft);
        recruit.answerAssignedQuestions();
        countdown.stop();
        exitRoutine.recruitResults();
        System.exit(0);
    }
}
