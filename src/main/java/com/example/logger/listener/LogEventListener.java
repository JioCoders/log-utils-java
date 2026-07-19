package com.example.logger.listener;

import com.example.logger.entity.AuditApiLogEntity;
import com.example.logger.entity.AuditErrLogEntity;
import com.example.logger.entity.AuditMsgLogEntity;
import com.example.logger.repository.BufferedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LogEventListener {

    private final BufferedRepository<AuditApiLogEntity> apiBf;
    private final BufferedRepository<AuditMsgLogEntity> msgBf;
    private final BufferedRepository<AuditErrLogEntity> errBf;

    /**
     * Listens to API LogEvents published anywhere in the application.
     */
    @EventListener
    public void onEvent(AuditApiLogEntity event) {
        apiBf.save(event);
    }

    /**
     * Listens to Message Events.
     */
    @EventListener
    public void onEvent(AuditMsgLogEntity event) {
        msgBf.save(event);
    }

    /**
     * Listens to Error Events.
     */
    @EventListener
    public void onEvent(AuditErrLogEntity event) {
        errBf.save(event);
    }

}
