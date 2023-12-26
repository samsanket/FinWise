package com.exp.FinWise.configuration;

import com.exp.FinWise.Resume.dto.RequirementDTO;
import com.exp.FinWise.Resume.model.RequirementModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Transactional
@Async
@Component
public class AsyncMethodCall {

    @Autowired
    RestTemplate restTemplate;

    public void callToPythonApi(RequirementModel requirementModel) {
        RequirementDTO requirementDTO= new RequirementDTO(requirementModel);

        String apiUrl = "http://10.12.13.49:5000/scrape";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RequirementDTO> requestEntity = new HttpEntity<>(requirementDTO, headers);

        restTemplate.postForEntity(apiUrl, requestEntity, String.class);
    }
}