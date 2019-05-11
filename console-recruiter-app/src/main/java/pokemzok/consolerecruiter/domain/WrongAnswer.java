package pokemzok.consolerecruiter.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WrongAnswer implements Answer{

    private String content;

}
