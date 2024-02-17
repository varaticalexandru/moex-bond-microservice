package org.alexv.moexservice.parser;

import org.alexv.moexservice.dto.MoexBondDto;

import java.util.List;

public interface Parser {
    List<MoexBondDto> parse(String xml);
}
