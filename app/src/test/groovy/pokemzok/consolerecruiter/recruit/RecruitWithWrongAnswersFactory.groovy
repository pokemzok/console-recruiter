package pokemzok.consolerecruiter.recruit

import pokemzok.consolerecruiter.domain.Recruit
import pokemzok.consolerecruiter.domain.RecruitMultipleAnswer
import pokemzok.consolerecruiter.domain.WrongAnswer

class RecruitWithWrongAnswersFactory extends MockRecruitFactory {

     Recruit createRecruit1() {
        def recruit = create()
        def questions = recruit.assignedQuestions
        questions.forEach { question ->
            recruit.recruitAnswerCollection().add(
                    new RecruitMultipleAnswer(question, question.correctAnswers, question.wrongAnswers)
            )
        }
        return recruit
    }

     Recruit createRecruit2() {
        def recruit = create()
        def questions = recruit.assignedQuestions
        def correctAnswersSize = questions.size() - 1
        for (int i = 0; i < (correctAnswersSize - 1); i++) {
            if (i < questions.size()) {
                def question = questions.getAt(i)
                recruit.recruitAnswerCollection().add(
                        new RecruitMultipleAnswer(question, question.correctAnswers, question.wrongAnswers)
                )
            }
        }
        def question = questions.getAt(questions.size() - 1)
        recruit.recruitAnswerCollection().add(
                new RecruitMultipleAnswer(question, question.correctAnswers, new ArrayList<WrongAnswer>())
        )
        return recruit
    }

}
