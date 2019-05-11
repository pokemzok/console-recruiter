package pokemzok.consolerecruiter.recruit

import pokemzok.consolerecruiter.domain.JsonObject
import pokemzok.consolerecruiter.domain.RecruitRate
import spock.lang.Specification
import spock.lang.Unroll

import java.math.RoundingMode

class LenientCountingStrategySpec extends Specification {

    private static final def recruitWithCorrectAnswersFactory = new RecruitWithCorrectAnswersFactory()
    private static final def recruitWithWrongAnswersFactory = new RecruitWithWrongAnswersFactory()
    private static final def recruitWithPartlyCorrectAnswersFactory = new RecruitWithPartlyCorrectAnswersFactory()

    @Unroll
    def "should correctly calculate user rate using University strategy"() {
        given:
        def strategy = new LenientCountingStrategy()
        when:
        glados.stopAnsweringQuestion()
        def lenientRate = glados.rate(strategy)
        then:
        new JsonObject(lenientRate).toString() == new JsonObject(
                new RecruitRate(points, maxPoints, rate, strategy.getClass().getSimpleName())
        ).toString()
        where:
        glados                                                  | maxPoints | points                          | rate
        recruitWithCorrectAnswersFactory.createRecruit1()       | 4L        | createBigDecimalWithScale(1L)   | createBigDecimalWithScale(25L)
        recruitWithCorrectAnswersFactory.createRecruit2()       | 4L        | createBigDecimalWithScale(2L)   | createBigDecimalWithScale(50L)
        recruitWithCorrectAnswersFactory.createRecruit3()       | 4L        | createBigDecimalWithScale(3L)   | createBigDecimalWithScale(75L)
        recruitWithCorrectAnswersFactory.createRecruit4()       | 4L        | createBigDecimalWithScale(4L)   | createBigDecimalWithScale(100L)
        recruitWithWrongAnswersFactory.createRecruit1()         | 4L        | createBigDecimalWithScale(0L)   | createBigDecimalWithScale(0L)
        recruitWithWrongAnswersFactory.createRecruit2()         | 4L        | createBigDecimalWithScale(1L)   | createBigDecimalWithScale(25L)
        recruitWithPartlyCorrectAnswersFactory.createRecruit1() | 4L        | createBigDecimalWithScale(0.33) | createBigDecimalWithScale(8.25)
        recruitWithPartlyCorrectAnswersFactory.createRecruit2() | 4L        | createBigDecimalWithScale(1.33) | createBigDecimalWithScale(33.25)
        recruitWithPartlyCorrectAnswersFactory.createRecruit3() | 4L        | createBigDecimalWithScale(2.33) | createBigDecimalWithScale(58.25)
        recruitWithCorrectAnswersFactory.createRecruit5()       | 1L        | createBigDecimalWithScale(1L)   | createBigDecimalWithScale(100L)
    }

    private static def createBigDecimalWithScale(long val) {
        return new BigDecimal(val).setScale(2, RoundingMode.HALF_UP)
    }

    private static def createBigDecimalWithScale(double val) {
        return new BigDecimal(val).setScale(2, RoundingMode.HALF_UP)
    }
}
