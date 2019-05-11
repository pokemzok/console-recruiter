package pokemzok.consolerecruiter.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@Slf4j
public class RecruitRate {

    private BigDecimal points;
    private long maximumPoints;
    private BigDecimal rate; // percents
    private String countingStrategyName;

    void printPoints() {
        log.info(countingStrategyName + " based rate. "
                + "You've got " + points
                + " of maximum " + maximumPoints
                + ". Your rate is: " + rate + "%.");
    }
}
