package pokemzok.consolerecruiter.domain

class RobotInputInterface implements InputInterface{

    @Override
    RecruitMultipleAnswer askForAnswer(Question question) {
        return new RecruitMultipleAnswer(
                question,
                question.shuffledAnswers().getCorrect(),
                question.shuffledAnswers().getWrong()
        )
    }

}
