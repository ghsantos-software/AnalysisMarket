package finacial.market_analysis.controller;

import finacial.market_analysis.dto.IndicatorResponseDTO;
import finacial.market_analysis.enums.EconomicIndicatorType;
import finacial.market_analysis.service.EconomicIndicatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/indicators")
public class EconomicIndicatorController {

    private final EconomicIndicatorService service;

    public EconomicIndicatorController(
            EconomicIndicatorService service
    ) {
        this.service = service;
    }

    @GetMapping("/ipca")
    public List<IndicatorResponseDTO> getIpca(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ) {
        return service.getIndicators(
                        433,
                        EconomicIndicatorType.IPCA.name(),
                        "%",
                        startDate,
                        endDate
                )
                .stream()
                .map(item -> new IndicatorResponseDTO(
                        item.name(),
                        item.value(),
                        item.unit(),
                        item.referenceDate(),
                        item.source()
                ))
                .toList();
    }
}
