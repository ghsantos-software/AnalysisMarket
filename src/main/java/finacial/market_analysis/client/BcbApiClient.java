package finacial.market_analysis.client;

import finacial.market_analysis.dto.BcbSeriesDTO;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class BcbApiClient {

    private final RestClient restClient;

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public BcbApiClient() {
        this.restClient = RestClient.builder()
                .baseUrl("https://api.bcb.gov.br/dados/serie")
                .build();
    }

    public List<BcbSeriesDTO> getSeries(
            int seriesCode,
            LocalDate startDate,
            LocalDate endDate
    ) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/bcdata.sgs.{code}/dados")
                        .queryParam("formato", "json")
                        .queryParam(
                                "dataInicial",
                                startDate.format(FORMATTER)
                        )
                        .queryParam(
                                "dataFinal",
                                endDate.format(FORMATTER)
                        )
                        .build(seriesCode))
                .retrieve()
                .body(new ParameterizedTypeReference<
                        List<BcbSeriesDTO>>() {});
    }
}
