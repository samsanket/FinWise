package com.exp.FinWise.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {

    @Value("${resume.python.api.base.url}")
    static String pythonBaseUrl;


   public static final  String  PYTHON_API_URL=pythonBaseUrl;
}
