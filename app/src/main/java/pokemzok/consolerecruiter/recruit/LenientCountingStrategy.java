package pokemzok.consolerecruiter.recruit;

import pokemzok.consolerecruiter.domain.CountingPointsStrategy;
import pokemzok.consolerecruiter.domain.Recruit;
import pokemzok.consolerecruiter.domain.RecruitMultipleAnswer;
import pokemzok.consolerecruiter.domain.RecruitRate;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Should count points in lenient way. If recruit picked 1 of the three good answers without picking wrong answer he should be rewarded 1/3 point.
 */
public class LenientCountingStrategy implements CountingPointsStrategy {

    private static final long PERCENTAGE_MULTIPLIER = 100L;
    private static int SCALE = 2;
    private static RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    @Override
    public RecruitRate countFor(Recruit recruit) {
        var points = countPoints(recruit);
        var maxPoints = countMaxPoints(recruit);
        return new RecruitRate(points, maxPoints, calculateRate(points, maxPoints), this.getClass().getSimpleName());
    }

    private BigDecimal countPoints(Recruit recruit) {
        return recruit.getAnsweredQuestions().stream().
                filter(this::isAnswerCorrect)
                .map(answer ->
                        answer.getQuestion().getCorrectAnswers().size() == 0 ?
                                BigDecimal.ONE.setScale(SCALE, ROUNDING_MODE)
                                :
                                new BigDecimal(answer.getCorrectAnswers().size())
                                        .divide(
                                                new BigDecimal(
                                                        answer.getQuestion().getCorrectAnswers().size()),
                                                SCALE,
                                                ROUNDING_MODE
                                        )
                ).reduce(BigDecimal.ZERO.setScale(SCALE, ROUNDING_MODE), BigDecimal::add);
    }

    private boolean isAnswerCorrect(RecruitMultipleAnswer answer) {
        return answer.getWrongAnswers().isEmpty();
    }

    private long countMaxPoints(Recruit recruit) {
        return recruit.getAnsweredQuestions().size() + recruit.getUnansweredQuestions().size();
    }

    private BigDecimal calculateRate(BigDecimal points, long maxPoints) {
        return points.multiply(BigDecimal.valueOf(PERCENTAGE_MULTIPLIER))
                .divide(BigDecimal.valueOf(maxPoints), RoundingMode.DOWN);
    }
}


