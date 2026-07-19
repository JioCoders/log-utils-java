package com.jiocoders.utils;

import com.example.logger.entity.AuditApiLogEntity;
import com.example.logger.entity.AuditErrLogEntity;
import com.example.logger.entity.AuditMsgLogEntity;
import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanUtils;

import java.util.function.Consumer;

@UtilityClass
public class EventConversionUtil {

    public static AuditMsgLogEntity addMsgLog(Consumer<AuditMsgLogEntity.AuditMsgLogEntityBuilder<?, ?>> consumer) {
        AuditMsgLogEntity.AuditMsgLogEntityBuilder<?, ?> builder = AuditMsgLogEntity.builder();
        consumer.accept(builder);
        return builder.build();
    }

    public static AuditErrLogEntity addErrLog(Consumer<AuditErrLogEntity.AuditErrLogEntityBuilder<?, ?>> consumer) {
        AuditErrLogEntity.AuditErrLogEntityBuilder<?, ?> builder = AuditErrLogEntity.builder();
        consumer.accept(builder);
        return builder.build();
    }

    public static AuditApiLogEntity addApiLog(Consumer<AuditApiLogEntity.AuditApiLogEntityBuilder<?, ?>> consumer) {
        AuditApiLogEntity.AuditApiLogEntityBuilder<?, ?> builder = AuditApiLogEntity.builder();
        builder.version(1);
        builder.requestTimeMs(System.currentTimeMillis());
        consumer.accept(builder);
        return builder.build();
    }

    public static void updateMsgLog(AuditMsgLogEntity log, Consumer<AuditMsgLogEntity.AuditMsgLogEntityBuilder<?, ?>> consumer) {
        AuditMsgLogEntity.AuditMsgLogEntityBuilder<?, ?> builder = log.toBuilder();
        consumer.accept(builder);
        AuditMsgLogEntity updated = builder.build();
        BeanUtils.copyProperties(updated, log);
    }

    public static void updateApiLog(AuditApiLogEntity log, Consumer<AuditApiLogEntity.AuditApiLogEntityBuilder<?, ?>> consumer) {
        AuditApiLogEntity.AuditApiLogEntityBuilder<?, ?> builder = log.toBuilder();
        builder.version(log.getVersion() + 1);
        builder.responseTimeMs(System.currentTimeMillis());
        consumer.accept(builder);
        AuditApiLogEntity updated = builder.build();
        BeanUtils.copyProperties(updated, log);
    }

}
