package pokemzok.consolerecruiter

import pokemzok.consolerecruiter.recruit.MockCountingStrategy
import spock.lang.Specification

class StrategiesPreconditionSpec extends Specification {

    def "should throw an exception because strategies list has illegal value"() {
        when:
        new StrategiesPrecondition().check(illegalValue)
        then:
        thrown(NoStrategySelectedException)
        where:
        illegalValue | _
        null         | _
        []           | _
    }

    def "should execute without an exception because strategies list has correct value"(){
        when:
        new StrategiesPrecondition().check([new MockCountingStrategy()])
        then:
        noExceptionThrown()
    }
}
