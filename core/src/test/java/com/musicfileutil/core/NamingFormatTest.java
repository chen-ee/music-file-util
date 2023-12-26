package com.musicfileutil.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Yi Chen
 * @date 2023-12-26 19:17
 * @since 1.0
 */
class NamingFormatTest {
    private NamingFormat namingFormat;

    @BeforeEach
    void setUp() {
        namingFormat = NamingFormat.ART_SONG;
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getCode() {
    }

    @Test
    void getName() {
        System.out.println(namingFormat.toString());
    }

    @Test
    void getByCode() {
    }

    @Test
    void getByName() {
    }
}