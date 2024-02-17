package org.alexv.moexservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.alexv.moexservice.model.Bond;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BondsDto {
    List<Bond> bonds;
}
