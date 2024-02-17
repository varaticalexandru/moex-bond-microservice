package org.alexv.moexservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MoexBondDto {
    private String ticker;
    private String name;
    private Double price;
}
