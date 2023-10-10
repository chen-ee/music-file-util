package com.musicfileutil.core;

import org.apache.commons.lang3.StringUtils;

/**
 * 音乐文件命名格式类型。
 *
 * @author Yi Chen
 * @date 2023-06-17 16:26
 * @since 1.0
 */
public enum NamingFormat {
    /**
     * 歌曲名-歌手名
     */
    SONG_ART("SONG_ART", 0),
    /**
     * 歌手名-歌曲名
     */
    ART_SONG("ART_SONG", 1);

    /**
     * 歌曲名和歌手名的分隔符：{@value}
     */
    public static final String SEPARATOR = " - ";

    private final String name;
    private final Integer code;

    NamingFormat(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public Integer getCode() {
        return code;
    }

    public static NamingFormat getByName(String name) throws UnknownNamingFormatException {
        NamingFormat result;
        try {
            result = valueOf(StringUtils.upperCase(name));
        } catch (IllegalArgumentException e) {
            throw new UnknownNamingFormatException();
        }
        return result;
    }
}
