package com.musicfileutil.core;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

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
    SONG_ART("NF001", "歌曲名-歌手名"),
    /**
     * 歌手名-歌曲名
     */
    ART_SONG("NF002", "歌手名-歌曲名");

    /**
     * 歌曲名和歌手名的分隔符：{@value}
     */
    public static final String SEPARATOR = " - ";

    private final String code;
    private final String name;
    /* 表驱动法使用的 Map */
    private static final Map<String, NamingFormat> codeMap;
    private static final Map<String, NamingFormat> nameMap;
    /* 表驱动法使用的 Map */

    NamingFormat(String code, String name) {
        this.code = code;
        this.name = name;
    }

    static {
        Map<String, NamingFormat> codeMapTemp = new HashMap<>(values().length);
        Map<String, NamingFormat> nameMapTemp = new HashMap<>(values().length);
        for (NamingFormat namingFormat : values()) {
            codeMapTemp.put(namingFormat.getCode(), namingFormat);
            nameMapTemp.put(namingFormat.getName(), namingFormat);
        }
        codeMap = ImmutableMap.copyOf(codeMapTemp);
        nameMap = ImmutableMap.copyOf(nameMapTemp);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    /**
     * @return 指定命名格式代码的枚举，如果没找到则返回 {@code null}
     */
    public static NamingFormat getByCode(String code) {
        return codeMap.get(code);
    }

    /**
     * @return 指定命名格式名称的枚举，如果没找到则返回 {@code null}
     */
    public static NamingFormat getByName(String name) {
        return nameMap.get(name);
    }

    @Override
    public String toString() {
        return getName();
    }
}
