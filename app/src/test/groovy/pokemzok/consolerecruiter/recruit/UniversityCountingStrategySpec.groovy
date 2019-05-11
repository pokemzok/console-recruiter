package pokemzok.consolerecruiter.recruit

import pokemzok.consolerecruiter.domain.JsonObject
import pokemzok.consolerecruiter.domain.RecruitRate
import spock.lang.Specification
import spock.lang.Unroll

class UniversityCountingStrategySpec extends Specification {

    private static final def recruitWithCorrectAnswersFactory = new RecruitWithCorrectAnswersFactory()
    private static final def recruitWithWrongAnswersFactory = new RecruitWithWrongAnswersFactory()
    private static final def recruitWithPartlyCorrectAnswersFactory = new RecruitWithPartlyCorrectAnswersFactory()

    @Unroll
    def "should correctly calculate user rate using University strategy"() {
        given:
        def strategy = new UniversityCountingStrategy()
        when:
        glados.stopAnsweringQuestion()
        def universityRate = glados.rate(strategy)
        then:
        new JsonObject(universityRate).toString() == new JsonObject(
                new RecruitRate(new BigDecimal(points), maxPoints, rate, strategy.getClass().getSimpleName())
        ).toString()

        where:
        glados                                                  | maxPoints | points | rate
        recruitWithCorrectAnswersFactory.createRecruit1()       | 4L        | 1L     | new BigDecimal(25L)
        recruitWithCorrectAnswersFactory.createRecruit2()       | 4L        | 2L     | new BigDecimal(50L)
        recruitWithCorrectAnswersFactory.createRecruit3()       | 4L        | 3L     | new BigDecimal(75L)
        recruitWithCorrectAnswersFactory.createRecruit4()       | 4L        | 4L     | new BigDecimal(100L)
        recruitWithWrongAnswersFactory.createRecruit1()         | 4L        | 0L     | BigDecimal.ZERO
        recruitWithWrongAnswersFactory.createRecruit2()         | 4L        | 1L     | new BigDecimal(25L)
        recruitWithPartlyCorrectAnswersFactory.createRecruit1() | 4L        | 0L     | BigDecimal.ZERO
        recruitWithPartlyCorrectAnswersFactory.createRecruit2() | 4L        | 1L     | new BigDecimal(25L)
        recruitWithPartlyCorrectAnswersFactory.createRecruit3() | 4L        | 1L     | new BigDecimal(25L)
        recruitWithCorrectAnswersFactory.createRecruit5()       | 1L        | 1L     | new BigDecimal(100L)
    }

}
