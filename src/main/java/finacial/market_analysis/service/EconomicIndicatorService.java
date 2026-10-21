package finacial.market_analysis.service;

import finacial.market_analysis.client.BcbApiClient;
import finacial.market_analysis.dto.BcbSeriesDTO;
import finacial.market_analysis.model.EconomicIndicator;
import org.springframework.stereotype.Service;
import finacial.market_analysis.exceptions.InvalidDateRangeException;

import java.time.LocalDate;
import java.util.List;

@Service
public class EconomicIndicatorService {

    private final BcbApiClient bcbApiClient;

    public EconomicIndicatorService(BcbApiClient bcbApiClient) {
        this.bcbApiClient = bcbApiClient;
    }

    public List<EconomicIndicator> getIndicators(
            int seriesCode,
            String name,
            String unit,
            LocalDate startDate,
            LocalDate endDate
    ) {
        if (startDate.isAfter(endDate)) {
            throw new InvalidDateRangeException(
                    "A data inicial não pode ser posterior à data final."
            );
        }
        List<BcbSeriesDTO> data = bcbApiClient.getSeries(
                seriesCode,
                startDate,
                endDate
        );

        return data.stream()
                .map(item -> new EconomicIndicator(
                        name,
                        item.value(),
                        unit,
                        item.date(),
                        "Banco Central do Brasil"
                ))
                .toList();
    }
}