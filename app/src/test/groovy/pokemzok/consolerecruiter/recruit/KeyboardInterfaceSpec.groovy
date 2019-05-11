package pokemzok.consolerecruiter.recruit

import pokemzok.consolerecruiter.domain.AnswerCollection
import pokemzok.consolerecruiter.domain.JsonObject
import pokemzok.consolerecruiter.domain.MockQuestionRepository
import pokemzok.consolerecruiter.domain.Question
import pokemzok.consolerecruiter.domain.RecruitMultipleAnswer
import spock.lang.Specification
import spock.lang.Unroll

import java.util.stream.Collectors

class KeyboardInterfaceSpec extends Specification {

    private static final def inputInterface = new KeyboardInterface()

    @Unroll
    def "should correctly classify user answers as wrong or correct"() {
        given:
        def question = new MockQuestionRepository().findAll().stream().findFirst().orElseThrow{new RuntimeException("Your collection is empty")}
        and:
        simulateKeyboardResponse(simulatedResponse)
        when:
        def multipleAnswer = inputInterface.askForAnswer(question)
        then:
        new JsonObject(multipleAnswer).toString() == new JsonObject(expectedAnswer(question, selectedAnswerNumbers)).toString()
        cleanup:
        System.setIn(System.in)
        where:
        simulatedResponse        | selectedAnswerNumbers
        "1"                      | [1]
        " 1"                     | [1]
        " 1 "                    | [1]
        "1, 2"                   | [1, 2]
        "1,  2"                  | [1, 2]
        " 1,  2, 3, 4, 5 "       | [1, 2, 3, 4, 5]
        " -1, 0, 999 "           | []
        " Java, Kotlin, Monika " | []
        "\n"                     | []
        " "                      | []
    }

    private static def simulateKeyboardResponse(String simulatedResponse) {
        ByteArrayInputStream input = new ByteArrayInputStream(simulatedResponse.getBytes())
        System.setIn(input)

    }

    private static def expectedAnswer(Question question, List<Integer> selectedAnswerNumbers) {
        def answers = new AnswerCollection(
                selectedAnswerNumbers.stream()
                        .map { it -> question.shuffledAnswers().getAll().get(answerNumberToIndex(it)) }
                        .collect(Collectors.toList())
        )
        return new RecruitMultipleAnswer(
                question,
                answers.getCorrect(),
                answers.getWrong()
        )
    }

    private static def answerNumberToIndex(int answerNumber) {
        return --answerNumber
    }
}
