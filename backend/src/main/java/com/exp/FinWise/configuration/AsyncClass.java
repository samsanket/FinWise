package com.exp.FinWise.configuration;

import com.exp.FinWise.Resume.Service.MailService;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Transactional
@Component
public class AsyncClass {
    private final static Logger logger = LoggerFactory.getLogger(AsyncClass.class);


  @Async("threadPoolTaskExecutor")
  public void handleAsync(Message message) {
      logger.info("Execute method asynchronously - {}" ,Thread.currentThread().getName());
  }

}
