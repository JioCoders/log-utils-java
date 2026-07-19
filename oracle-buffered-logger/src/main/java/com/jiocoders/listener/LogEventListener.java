package com.jiocoders.listener;

import com.jiocoders.entity.AuditApiLogEntity;
import com.jiocoders.entity.AuditErrLogEntity;
import com.jiocoders.entity.AuditMsgLogEntity;
import com.jiocoders.repository.BufferedRepository;

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
