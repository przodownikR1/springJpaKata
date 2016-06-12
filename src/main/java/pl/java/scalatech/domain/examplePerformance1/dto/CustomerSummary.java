package pl.java.scalatech.domain.examplePerformance1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerSummary {

    private Long customerId;
    private String name;
    private long jobCount;

}
