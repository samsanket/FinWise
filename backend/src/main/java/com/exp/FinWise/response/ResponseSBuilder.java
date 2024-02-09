package com.exp.FinWise.response;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class ResponseSBuilder {

    public ResponseCommon createResponse(Object response, Integer statusCode){
        ResponseCommon responseResume = new ResponseCommon();
        responseResume.setResponse(response);
        responseResume.setStatusCode(statusCode);
        return responseResume;
    }
    public ResponseCommon createResponse(Object response){
        ResponseCommon responseResume = new ResponseCommon();
        responseResume.setStatusCode(HttpStatus.OK.value());
        responseResume.setResponse(response);
        return responseResume;
    }
}
