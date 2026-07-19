package com.example.logger.controller;

import com.example.logger.entity.AuditApiLogEntity;
import com.example.logger.entity.AuditErrLogEntity;
import com.example.logger.entity.AuditMsgLogEntity;
import com.example.logger.utils.EventConversionUtil;
import com.example.logger.utils.LogType;
import com.jiocoders.MainWorker;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LogTestController {

    private final ApplicationEventPublisher eventPublisher;

    /**
     * Endpoint to simulate API log generation.
     * Example usage: GET /test-api-log?method=GET&endpoint=/api/users&status=200&responseTime=150
     * curl "http://localhost:8080/api-log?msg=HelloOracle&level=INFO&headers=dymmyHeaders&method=iiiiI&endpoint=aaaI&statusCode=200&responseTimeMs=1000&requestBody=reqI&responseBody=resI"
     */
    @GetMapping("/api-log")
    public String triggerApiLog(@RequestParam String method,
                                @RequestParam String endpoint,
                                @RequestParam String headers,
                                @RequestParam Integer statusCode,
                                @RequestParam(required = false) String requestBody,
                                @RequestParam(required = false) String responseBody) throws InterruptedException {

        MainWorker worker = new MainWorker();
        worker.check();
        AuditApiLogEntity log = EventConversionUtil.addApiLog(builder -> builder
                .method(method)
                .endpoint(endpoint)
                .headers(headers)
                .statusCode(statusCode)
                .requestBody(requestBody));
        // Publish the err log event
        eventPublisher.publishEvent(log);

//        Thread.sleep(10000); // Simulate processing time
        EventConversionUtil.updateApiLog(log, builder -> builder.responseBody(responseBody));
        // Publish the event asynchronously/synchronously
        eventPublisher.publishEvent(log);
        return "API log event published: " + method + " " + endpoint;
    }

    /**
     * Endpoint to simulate Message log generation.
     * Example usage: GET /msg-log?msg=HelloOracle&key=INFO
     * curl "http://localhost:8080/msg-log?key=keyInfo&msg=HelloOracle&level=INFO"
     */
    @GetMapping("/msg-log")
    public String triggerMsgLog(@RequestParam String key, @RequestParam String msg, @RequestParam(defaultValue = "INFO") String level) {
        AuditMsgLogEntity log = EventConversionUtil.addMsgLog(builder -> builder.type(LogType.MSG_LOG).key(key).message(msg));
        eventPublisher.publishEvent(log);
        return "Msg log event published: " + key + " " + msg;
    }

    /**
     * Endpoint to simulate error log generation.
     * Example usage: GET /err-log?msg=HelloOracle&key=ERR
     * curl "http://localhost:8080/err-log?key=keyInfo&msg=ErrOracle&level=INFO"
     */
    @GetMapping("/err-log")
    public String triggerApiLog(@RequestParam String key, @RequestParam String msg) {
        AuditErrLogEntity log = EventConversionUtil.addErrLog(builder -> builder.type(LogType.ERROR_LOG).key(key).message(msg));
        eventPublisher.publishEvent(log);
        return "Err log event published: " + msg;
    }

}
