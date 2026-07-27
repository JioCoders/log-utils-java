package com.jiocoders.dao;

import com.jiocoders.entity.AuditApiLogEntity;
import com.jiocoders.entity.AuditErrLogEntity;
import com.jiocoders.entity.AuditMsgLogEntity;
import com.jiocoders.utils.PersistentEvent;
import com.jiocoders.utils.MessageType;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuditLogDao {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final ApplicationEventPublisher eventPublisher;

    /**
     * Method Name : publishApiRequest
     * <p>
     * Description : Set publish request time and prepare for publishing.
     * </p>
     *
     * @param logEntity The Entity representing the current state of the API log which has to be published
     */
    public void publishApiRequest(AuditApiLogEntity logEntity) {
        logEntity.setMessage(MessageType.SUCCESS.getLabel());
        logEntity.setRemark("Api request sent.");
        logEntity.setRequestTimeMs(System.currentTimeMillis());
        sendApiLog(logEntity);
    }

    /**
     * Method Name : publishApiResponse
     * <p>
     * Description : Set publish response time and prepare for publishing.
     * </p>
     *
     * @param logEntity The Entity representing the current state of the API log which has to be published
     */
    public void publishApiResponse(AuditApiLogEntity logEntity) {
        logEntity.setMessage(MessageType.SUCCESS.getLabel());
        logEntity.setRemark("Api response received.");
        logEntity.setResponseTimeMs(System.currentTimeMillis());
        sendApiLog(logEntity);
    }
    /**
     * Method Name : sendErrorEvent
     * <p>
     * Description : Set exception status and prepare for publishing.
     * </p>
     *
     * @param remark            Additional message for error or exception
     * @param logEntity The AuditApiLogEntity representing the current state of the API log which has to be published
     */
    public void sendErrorEvent(AuditApiLogEntity logEntity, String remark) {
        logEntity.setMessage(MessageType.FAILED.getLabel());
        logEntity.setRemark(remark);
        sendApiLog(logEntity);
    }

    /**
     * Method Name : sendEvent
     * <p>
     * Description : Publishing api log event.
     * </p>
     *
     * @param logEntity The Entity containing the log details to be saved.
     */
    private void sendApiLog(AuditApiLogEntity logEntity) {
        try {
            PersistentEvent<AuditApiLogEntity> event = new PersistentEvent<>(AuditApiLogEntity.class, logEntity);
            eventPublisher.publishEvent(event);
        } catch (Exception e) {
            log.error("Failed to save details in Request Response History. Error: {}", e.getMessage());
        }
    }

    /**
     * Method Name : publishErrorLog
     * <p>
     * Description : Set error log and prepare for publishing.
     * </p>
     *
     * @param logEntity The Entity representing the current state of the Error log which has to be published.
     */
    public void publishErrorLog(AuditErrLogEntity logEntity) {
        sendErrorEvent(logEntity);
    }

    /**
     * Method Name : sendErrorEvent
     * <p>
     * Description : publishing error log event.
     * </p>
     *
     * @param logEntity The Entity containing the log details to be saved.
     */
    private void sendErrorEvent(AuditErrLogEntity logEntity) {
        try {
            PersistentEvent<AuditErrLogEntity> event = new PersistentEvent<>(AuditErrLogEntity.class, logEntity);
            eventPublisher.publishEvent(event);
        } catch (Exception e) {
            log.error("Failed to save details in Request Response History. Error: {}", e.getMessage());
        }
    }

    /**
     * Method Name : publishMessageLog
     * <p>
     * Description : Set message log and prepare for publishing.
     * </p>
     *
     * @param logEntity The Entity representing the current state of the API log which has to be published
     */
    public void publishMessageLog(AuditMsgLogEntity logEntity) {
        sendMessageEvent(logEntity);
    }

    /**
     * Method Name : sendMessageEvent
     * <p>
     * Description : Publishing message log event.
     * </p>
     *
     * @param logEntity The Entity containing the log details to be saved.
     */
    private void sendMessageEvent(AuditMsgLogEntity logEntity) {
        try {
            PersistentEvent<AuditMsgLogEntity> event = new PersistentEvent<>(AuditMsgLogEntity.class, logEntity);
            eventPublisher.publishEvent(event);
        } catch (Exception e) {
            log.error("Failed to save details in Request Response History. Error: {}", e.getMessage());
        }
    }

}
