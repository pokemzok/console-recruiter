package pokemzok.consolerecruiter.domain

import pokemzok.consolerecruiter.recruit.MockRecruitFactory
import spock.lang.Specification

class RecruitPreconditionsSpec extends Specification {

    private static final recruitFactory = new MockRecruitFactory()
    private static final preconditions = new RecruitPreconditions()

    def "recruit should pass preconditions check"() {
        given:
        def recruit = recruitFactory.create()
        when:
        preconditions.check(recruit)
        then:
        noExceptionThrown()
    }

    // apparently data driven tests does not work for my case with exception check (find out why)
    def "preconditions should throw an exception because recruit does not have a name"() {
        when:
        preconditions.check(recruitFactory.createAnEmptyRecruit())
        then:
        def ex = thrown(RuntimeException)
        ex.message == "Recruit name can not be null"
    }

    def "preconditions should throw an exception because recruit does not have an input interface"() {
        when:
        preconditions.check(recruitFactory.createRecruitWithOnlyName())
        then:
        def ex = thrown(RuntimeException)
        ex.message == "Input interface was not selected for the recruit"
    }

    def "preconditions should throw an exception because recruit does not have any question assigned"() {
        when:
        preconditions.check(recruitFactory.createRecruitWithoutAssignedQuestions())
        then:
        def ex = thrown(RuntimeException)
        ex.message == "No question assigned to user. Make sure there is some questions in db."
    }


}
