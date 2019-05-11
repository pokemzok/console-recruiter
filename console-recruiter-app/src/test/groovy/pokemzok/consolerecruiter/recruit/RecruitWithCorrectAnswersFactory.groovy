package pokemzok.consolerecruiter.recruit

import pokemzok.consolerecruiter.domain.*

class RecruitWithCorrectAnswersFactory extends MockRecruitFactory {

    Recruit createRecruit1() {
        def recruit = create()
        def questions = recruit.assignedQuestions
        def question = questions.stream().findFirst().orElseThrow {
            new RuntimeException("There is no question in collection")
        }
        recruit.recruitAnswerCollection().add(
                new RecruitMultipleAnswer(question, question.correctAnswers, new ArrayList<WrongAnswer>())
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
                    new RecruitMultipleAnswer(question, question.correctAnswers, new ArrayList<WrongAnswer>())
            )
        }
        return recruit
    }

    Recruit createRecruit3() {
        def recruit = create()
        def questions = recruit.assignedQuestions
        def correctAnswersSize = questions.size()
        for (int i = 0; i < (correctAnswersSize - 1); i++) {
            def question = questions.getAt(i)
            recruit.recruitAnswerCollection().add(
                    new RecruitMultipleAnswer(question, question.correctAnswers, new ArrayList<WrongAnswer>())
            )
        }
        return recruit
    }

    Recruit createRecruit4() {
        def recruit = create()
        def questions = recruit.assignedQuestions
        questions.forEach { question ->
            recruit.recruitAnswerCollection().add(
                    new RecruitMultipleAnswer(question, question.correctAnswers, new ArrayList<WrongAnswer>())
            )
        }
        return recruit
    }

    Recruit createRecruit5(){
        def recruit = new Recruit(TEST_USER_NAME, new RobotInputInterface(),
                [new Question("I do not have correct answer!", [],[new WrongAnswer("Wrong answer")])]
        )
        recruit.assignedQuestions.forEach{ question ->
            recruit.recruitAnswerCollection().add(
                    new RecruitMultipleAnswer(question, question.correctAnswers, [])
            )
        }
        return recruit
    }
}
