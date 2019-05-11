package pokemzok.consolerecruiter.recruit;

import pokemzok.consolerecruiter.domain.CountingPointsStrategy;
import pokemzok.consolerecruiter.domain.Recruit;
import pokemzok.consolerecruiter.domain.RecruitMultipleAnswer;
import pokemzok.consolerecruiter.domain.RecruitRate;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Should count points like professor on University either 0 if anything is incorrect or 1 if all answers is correct
 */
public class UniversityCountingStrategy implements CountingPointsStrategy {

    private static final long PERCENTAGE_MULTIPLIER = 100L;

    @Override
    public RecruitRate countFor(Recruit recruit) {
        var points = recruit.getAnsweredQuestions().stream().filter(this::isAnswerCorrect).count();
        var maxPoints = countMaxPoints(recruit);
        return new RecruitRate(new BigDecimal(points), maxPoints, calculateRate(points, maxPoints), this.getClass().getSimpleName());
    }

    private boolean isAnswerCorrect(RecruitMultipleAnswer answer) {
        return answer.getWrongAnswers().isEmpty() &&
                answer.getCorrectAnswers().size() == answer.getQuestion().getCorrectAnswers().size();
    }

    private long countMaxPoints(Recruit recruit) {
        return recruit.getAnsweredQuestions().size() + recruit.getUnansweredQuestions().size();
    }

    private BigDecimal calculateRate(long points, long maxPoints) {
        return BigDecimal.valueOf(points).multiply(BigDecimal.valueOf(PERCENTAGE_MULTIPLIER))
                .divide(BigDecimal.valueOf(maxPoints), RoundingMode.DOWN);
    }
}


