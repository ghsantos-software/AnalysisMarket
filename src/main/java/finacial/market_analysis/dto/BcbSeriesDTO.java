package finacial.market_analysis.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BcbSeriesDTO(

        @JsonProperty("data")
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate date,

        @JsonProperty("valor")
        BigDecimal value

) {}
