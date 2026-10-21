package finacial.market_analysis.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EconomicIndicator(
        String name,
        BigDecimal value,
        String unit,
        LocalDate referenceDate,
        String source
) {
}
