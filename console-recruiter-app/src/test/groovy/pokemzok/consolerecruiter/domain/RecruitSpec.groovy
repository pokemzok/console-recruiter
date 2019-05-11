package pokemzok.consolerecruiter.domain

import pokemzok.consolerecruiter.recruit.MockRecruitFactory
import pokemzok.consolerecruiter.recruit.RecruitWithCorrectAnswersFactory
import spock.lang.Specification

class RecruitSpec extends Specification {

    def "should start poll and answer all the questions"() {
        given:
        def glados = new MockRecruitFactory().create()
        def questions = glados.getAssignedQuestions()
        def copiedQuestions = new ArrayList(questions)
        when:
        glados.answerAssignedQuestions()
        then:
        glados.answeredQuestions.size() == copiedQuestions.size()
        countAllRecruitAnswersSize(glados) == getAllAnswersSize(copiedQuestions)
    }

    def "should get an exception after trying to rate recruit which is still answering questions"() {
        given:
        def glados = new RecruitWithCorrectAnswersFactory().createRecruit1()
        def strategy = Mock(CountingPointsStrategy)
        when:
        glados.rate(strategy)
        then:
        thrown(RateIsUnavailableException.class)
    }

    private static int countAllRecruitAnswersSize(Recruit recruit) {
        return countCorrectRecruitAnswers(recruit) + countWrongRecruitAnswers(recruit)
    }

    private static int countCorrectRecruitAnswers(Recruit recruit) {
        return recruit.answeredQuestions.stream()
                .map { it -> it.correctAnswers }
                .flatMap { it -> it.stream() }
                .count()
    }

    private static int countWrongRecruitAnswers(Recruit recruit) {
        return recruit.answeredQuestions.stream()
                .map { it -> it.wrongAnswers }
                .flatMap { it -> it.stream() }
                .count()
    }

    private static int getAllAnswersSize(Collection<Question> questions) {
        return questions.stream()
                .map { it -> it.shuffledAnswers().getAll() }
                .flatMap { it -> it.stream() }
                .count()
    }

}
