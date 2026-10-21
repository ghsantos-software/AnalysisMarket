package finacial.market_analysis.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record IndicatorResponseDTO(
        String name,
        BigDecimal value,
        String unit,
        LocalDate referenceDate,
        String source
) {
}
