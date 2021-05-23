package com.epam.ems.audit;

import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

@Component
public class AuditListener {

    private AuditLogger logger = new AuditLogger();

    @PrePersist
    public void onPrePersist(Object object) {
        logger.doLog("persist object :" + object);
    }

    @PreUpdate
    public void onPreUpdate(Object object) {
        logger.doLog("update object :" + object);
    }

    @PreRemove
    public void onPreRemove(Object object) {
        logger.doLog("remove object :" + object);
    }
}
