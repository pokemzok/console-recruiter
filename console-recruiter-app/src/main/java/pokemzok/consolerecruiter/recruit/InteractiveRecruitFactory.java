package pokemzok.consolerecruiter.recruit;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pokemzok.consolerecruiter.domain.*;

import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class InteractiveRecruitFactory implements RecruitFactory {

    private final RecruitRepository recruitRepository;
    private final QuestionRepository questionRepository;
    private final InputInterface inputInterface;

    @Override
    public Recruit create() {
        Scanner scanner = new Scanner(System.in);
        var userName = "";
        while (userName.isEmpty()) {
            userName = getUsernameWith(scanner).trim();
        }
        System.out.println("Hello " + userName);
        return recruitRepository.save(
                new Recruit(userName, inputInterface, questionRepository.findAll())
        );
    }

    private String getUsernameWith(Scanner scanner) {
        System.out.println("Enter username");
        return scanner.nextLine();
    }
}
