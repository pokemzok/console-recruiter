package pokemzok.consolerecruiter.domain

import spock.lang.Specification

class AnswerCollectionSpec extends Specification {

    def "should return expected quantity of correct answers"() {
        given:
        def answers = getAnswers()
        when:
        def correctAnswers = answers.getCorrect()
        then:
        correctAnswers.size() == 3
    }

    def "should return expected quantity of wrong answers"() {
        given:
        def answers = getAnswers()
        when:
        def wrongAnswers = answers.getWrong()
        then:
        wrongAnswers.size() == 2
    }

    def static getAnswers() {
        return new Question(
                "Which programming language is the best?",
                List.of(new CorrectAnswer("Java"), new CorrectAnswer("C#"), new CorrectAnswer("Kotlin")),
                List.of(new WrongAnswer("English"), new WrongAnswer("Japanese"))
        ).shuffledAnswers()
    }
}
