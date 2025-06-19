package com.projekat.nastavnik_service.service;

import com.projekat.nastavnik_service.entity.IshodIspita;
import com.projekat.nastavnik_service.repository.InstrumentEvaluacijeRepository;
import com.projekat.nastavnik_service.repository.IshodIspitaRepository;
import com.projekat.nastavnik_service.wrapper.IshodIspitaList;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class UploadXmlService {
    @Autowired
    private IshodIspitaRepository ishodIspitaRepository;
    @Autowired
    private InstrumentEvaluacijeRepository instrumentEvaluacijeRepository;

    @Async
    public CompletableFuture<ResponseEntity<?>> uploadXmlFromString(String xmlContent) {
        try (InputStream is = new ByteArrayInputStream(xmlContent.getBytes(StandardCharsets.UTF_8))) {
            JAXBContext context = JAXBContext.newInstance(IshodIspitaList.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new ClassPathResource("ishodi.xsd").getFile());

            unmarshaller.setSchema(schema);

            unmarshaller.setEventHandler(event -> {
                System.err.println("Validation Warning/Error: " + event.getMessage());
                return false;
            });

            IshodIspitaList wrapper = (IshodIspitaList) unmarshaller.unmarshal(is);
            List<IshodIspita> ishodi = wrapper.getIshodi();

            for (IshodIspita i : ishodi) {
                i.setDatumUnosa(LocalDateTime.now());
                long iiid= i.getInstrumentId();
                i.setInstrumentEvaluacije(instrumentEvaluacijeRepository.findById(iiid)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid Instrument: " + iiid)));
            }

            ishodIspitaRepository.saveAll(ishodi);
            return CompletableFuture.completedFuture(ResponseEntity.ok("Saved " + ishodi.size() + " records."));
        } catch (Exception e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("Error: " + e.getMessage()));
        }
    }
}
