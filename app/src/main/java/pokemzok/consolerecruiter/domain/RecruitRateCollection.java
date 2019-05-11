package pokemzok.consolerecruiter.domain;

import lombok.RequiredArgsConstructor;

import java.util.Collection;

@RequiredArgsConstructor
public class RecruitRateCollection {

    private final Collection<RecruitRate> rates;

    void add(RecruitRate rate) {
        rates.add(rate);
    }

    public void printRates() {
        rates.forEach(RecruitRate::printPoints);
    }

    public int size(){
        return rates.size();
    }
}
