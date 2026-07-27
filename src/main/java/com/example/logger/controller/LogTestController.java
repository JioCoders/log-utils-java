package com.example.logger.controller;

import com.jiocoders.dao.AuditLogDao;
import com.jiocoders.entity.AuditApiLogEntity;
import com.jiocoders.entity.AuditErrLogEntity;
import com.jiocoders.entity.AuditMsgLogEntity;
import com.jiocoders.utils.EventConversionUtil;
import com.jiocoders.utils.LogType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/audit")
public class LogTestController {

    private final AuditLogDao auditLogDao;

    public LogTestController(AuditLogDao auditLogDao) {
        this.auditLogDao = auditLogDao;
    }

    /**
     * Endpoint to simulate API log generation.
     * Example usage: GET /test-api-log?method=GET&endpoint=/api/users&status=200&responseTime=150
     * curl "http://localhost:8080/audit/api-log?msg=HelloOracle&level=INFO&headers=dymmyHeaders&method=iiiiI&endpoint=aaaI&statusCode=200&responseTimeMs=1000&requestBody=reqI&responseBody=resI"
     */
    @GetMapping("/api-log")
    public String triggerApiLog(@RequestParam String method,
                                @RequestParam String endpoint,
                                @RequestParam String headers,
                                @RequestParam Integer statusCode,
                                @RequestParam(required = false) String requestBody,
                                @RequestParam(required = false) String responseBody) {

        AuditApiLogEntity logEntity = EventConversionUtil.addApiLog(builder -> builder
                .method(method)
                .endpoint(endpoint)
                .headers(headers)
                .statusCode(statusCode)
                .requestBody(requestBody));
        // Publish the err log event
        auditLogDao.publishApiRequest(logEntity);

        EventConversionUtil.updateApiLog(logEntity, builder -> builder.responseBody(responseBody));
        // Publish the event asynchronously/synchronously
        auditLogDao.publishApiResponse(logEntity);
        return "API log event published: " + method + " " + endpoint;
    }

    /**
     * Endpoint to simulate Message log generation.
     * Example usage: GET /msg-log?msg=HelloOracle&key=INFO
     * curl "http://localhost:8080/audit/msg-log?key=keyInfo&msg=HelloOracle&level=INFO"
     */
    @GetMapping("/msg-log")
    public String triggerMessageLog(@RequestParam String key, @RequestParam String msg, @RequestParam(defaultValue = "INFO") String level) {
        AuditMsgLogEntity log = EventConversionUtil.addMsgLog(builder -> builder.type(LogType.MSG_LOG).key(key).message(msg));
        auditLogDao.publishMessageLog(log);
        return "Msg log event published: " + key + " " + msg;
    }

    /**
     * Endpoint to simulate error log generation.
     * Example usage: GET /err-log?msg=HelloOracle&key=ERR
     * curl "http://localhost:8080/audit/err-log?key=keyInfo&msg=ErrOracle&level=INFO"
     */
    @GetMapping("/err-log")
    public String triggerErrorLog(@RequestParam String key, @RequestParam String msg) {
        AuditErrLogEntity log = EventConversionUtil.addErrLog(builder -> builder.type(LogType.ERROR_LOG).key(key).message(msg));
        auditLogDao.publishErrorLog(log);
        return "Err log event published: " + msg;
    }

}
