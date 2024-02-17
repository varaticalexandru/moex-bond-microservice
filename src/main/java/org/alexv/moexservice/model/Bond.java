package org.alexv.moexservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bond {
    private String ticker;
    private String figi;
    private String name;
    private Currency currency;
    private String type;
    private String source;
}
