package michal.ulik.recruitmenttask.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import michal.ulik.recruitmenttask.model.dtos.RateDto;
import michal.ulik.recruitmenttask.model.dtos.ResultDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ResultService {
    private final RateService rateService;

    public ResultDto convertCurrency(String formCode, String toCode, BigDecimal amount){
        RateDto from = rateService.getRate(formCode);
        RateDto to = rateService.getRate(toCode);

        BigDecimal valueTo = to.getMid();
        BigDecimal valueFrom = from.getMid();
        BigDecimal result = valueFrom.divide(valueTo, RoundingMode.FLOOR).multiply(amount);

        return ResultDto.builder()
                .from(from.getCurrency())
                .to(to.getCurrency())
                .value(result)
                .build();
    }

    public List<ResultDto> convertCurrencies(String formCode){
        List<RateDto> allRates = rateService.getAllRates();
        return allRates.stream().map(rate -> convertCurrency(formCode, rate.getCode(), BigDecimal.ONE)).toList();
    }
}
