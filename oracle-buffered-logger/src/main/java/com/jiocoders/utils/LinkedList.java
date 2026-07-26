package com.jiocoders.utils;

/**
 * "Sum Type" representation of a linked list.
 */
public sealed interface LinkedList<T> {

    /**
     * An empty list.
     */
    record Empty<T>() implements LinkedList<T> {}

    /**
     * A not empty list.
     */
    record NotEmpty<T>(T first, LinkedList<T> rest) implements LinkedList<T> {}
}