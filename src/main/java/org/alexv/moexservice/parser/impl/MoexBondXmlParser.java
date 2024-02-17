package org.alexv.moexservice.parser.impl;

import lombok.extern.slf4j.Slf4j;
import org.alexv.moexservice.dto.MoexBondDto;
import org.alexv.moexservice.exception.BondParsingException;
import org.alexv.moexservice.parser.Parser;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class MoexBondXmlParser implements Parser {

    @Override
    public List<MoexBondDto> parse(String xml) {
        var bonds = new ArrayList<MoexBondDto>();

        var dbf = DocumentBuilderFactory.newInstance();
        dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            var db = dbf.newDocumentBuilder();

            try (var reader = new StringReader(xml)) {
                Document doc = db.parse(new InputSource(reader));
                doc.getDocumentElement().normalize();

                NodeList nodeList = doc.getElementsByTagName("row");

                for (var rowIdx = 0; rowIdx < nodeList.getLength(); rowIdx++) {
                    Node node = nodeList.item(rowIdx);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        var element = (Element) node;
                        String ticker = element.getAttribute("SECID");
                        String price = element.getAttribute("PREVPRICE");
                        String name = element.getAttribute("SHORTNAME");

                        if (!ticker.isEmpty() && !price.isEmpty() && !name.isEmpty()) {
                            bonds.add(
                                    MoexBondDto.builder()
                                            .name(name)
                                            .price(Double.parseDouble(price))
                                            .ticker(ticker)
                                            .build()
                            );
                        }

                    }
                }

            }
        } catch (Exception ex) {
            log.error("XML parsing error: {}.", xml, ex);
            throw new BondParsingException("XML parsing.");
        }
        return bonds;
    }
}
