package com.epam.ems.audit;

import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Log
public class AuditLogger {
    public void doLog(String message) {
        log.info(message);
    }
}
