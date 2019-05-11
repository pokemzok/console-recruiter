package pokemzok.consolerecruiter.recruit

import pokemzok.consolerecruiter.domain.Recruit
import pokemzok.consolerecruiter.domain.RecruitMultipleAnswer
import pokemzok.consolerecruiter.domain.WrongAnswer

class RecruitWithPartlyCorrectAnswersFactory extends MockRecruitFactory {

    Recruit createRecruit1() {
        def recruit = create()
        def questions = recruit.assignedQuestions
        def question = questions.stream().findFirst().orElseThrow {
            new RuntimeException("There is no question in collection")
        }
        recruit.recruitAnswerCollection().add(
                new RecruitMultipleAnswer(
                        question,
                        List.of(
                                question.correctAnswers.stream().findFirst().orElseThrow {
                                    new RuntimeException("There is no correct answers in collection")
                                }
                        ),
                        new ArrayList<WrongAnswer>()
                )
        )
        return recruit
    }

    Recruit createRecruit2() {
        def recruit = create()
        def questions = recruit.assignedQuestions
        def correctAnswersSize = questions.size() - 1
        for (int i = 0; i < (correctAnswersSize - 1); i++) {
            def question = questions.getAt(i)
            recruit.recruitAnswerCollection().add(
                    new RecruitMultipleAnswer(
                            question,
                            List.of(
                                    question.correctAnswers.stream().findFirst().orElseThrow {
                                        new RuntimeException("There is no correct answers in collection")
                                    }
                            ),
                            new ArrayList<WrongAnswer>())
            )
        }
        return recruit
    }

    Recruit createRecruit3() {
        def recruit = create()
        def questions = recruit.assignedQuestions
        questions.forEach { question ->
            recruit.recruitAnswerCollection().add(
                    new RecruitMultipleAnswer(
                            question,
                            List.of(
                                    question.correctAnswers.stream().findFirst().orElseThrow {
                                        new RuntimeException("There is no correct answers in collection")
                                    }
                            ),
                            new ArrayList<WrongAnswer>())
            )
        }
        return recruit
    }
}
