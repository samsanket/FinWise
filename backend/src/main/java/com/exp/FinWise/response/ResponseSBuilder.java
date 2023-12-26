package com.exp.FinWise.response;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class ResponseSBuilder {

    public ResponseResume createResponse(Object response, Integer statusCode){
        ResponseResume responseResume = new ResponseResume();
        responseResume.setResponse(response);
        responseResume.setStatusCode(statusCode);
        return responseResume;
    }
    public ResponseResume createResponse(Object response){
        ResponseResume responseResume = new ResponseResume();
        responseResume.setStatusCode(HttpStatus.OK.value());
        responseResume.setResponse(response);
        return responseResume;
    }
}
