package michal.ulik.recruitmenttask.model.dtos;

import lombok.*;
import lombok.experimental.SuperBuilder;
import michal.ulik.recruitmenttask.model.dtos.CurrencyDto;
import michal.ulik.recruitmenttask.model.dtos.RateDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class NbpTableDto{
    private List<RateDto> rates;
}
