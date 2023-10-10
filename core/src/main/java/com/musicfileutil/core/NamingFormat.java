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
    SONG_ART,
    /**
     * 歌手名-歌曲名
     */
    ART_SONG;

    public static NamingFormat getByString(String code) {
        // XXX 优化if-else代码结构
        if (StringUtils.equalsIgnoreCase(code, "SONG_ARG")) {
            return SONG_ART;
        } else if (StringUtils.equalsIgnoreCase(code, "ART_SONG")) {
            return SONG_ART;
        } else {
            return null;
        }
    }
}
