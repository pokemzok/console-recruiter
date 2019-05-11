package pokemzok.consolerecruiter.recruit


import pokemzok.consolerecruiter.domain.RecruitRepository
import spock.lang.Specification
import spock.lang.Unroll

class RecruitExitRoutineSpec extends Specification {

    private static final def recruitFactory = new MockRecruitFactory()
    private def repo = Mock(RecruitRepository)

    @Unroll
    def "should rate recruit using provided counting strategies"() {
        given:
        def recruit = Spy(recruitFactory.create())
        and:
        repo.save(recruit) >> recruit
        when:
        new RecruitExitRoutine(recruit, repo, strategies).recruitResults()
        then:
        recruit.rateCollection().size() == ratesSize
        1 * recruit.stopAnsweringQuestion()
        1 * repo.save(recruit)
        where:
        ratesSize | strategies
        1         | [new MockCountingStrategy()]
        2         | [new MockCountingStrategy(), new MockCountingStrategy()]
        3         | [new MockCountingStrategy(), new MockCountingStrategy(), new MockCountingStrategy()]
    }

}
