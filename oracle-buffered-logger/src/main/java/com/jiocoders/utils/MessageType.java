package com.jiocoders.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageType {
    SUCCESS("SUCCESS"), FAILED("FAILED");

    private final String label;
}