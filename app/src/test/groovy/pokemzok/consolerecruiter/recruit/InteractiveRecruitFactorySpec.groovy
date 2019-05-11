package pokemzok.consolerecruiter.recruit

import pokemzok.consolerecruiter.domain.MockQuestionRepository
import pokemzok.consolerecruiter.domain.RobotInputInterface
import spock.lang.Specification

class InteractiveRecruitFactorySpec extends Specification {

    private static final def recruitService = new InteractiveRecruitFactory(new MockRecruitRepository(), new MockQuestionRepository(), new RobotInputInterface())

    def "should create recruit "() {
        given:
        def recruitName = "GLaDOS"
        and:
        simulateInput(recruitName)
        when:
        def recruit = recruitService.create()
        then:
        recruit.name == recruitName
        recruit.answeredQuestions.size() == 0
        cleanup:
        System.setIn(System.in)
    }

    private static def simulateInput(String recruitName) {
        ByteArrayInputStream input = new ByteArrayInputStream(recruitName.getBytes())
        System.setIn(input)
    }
}
