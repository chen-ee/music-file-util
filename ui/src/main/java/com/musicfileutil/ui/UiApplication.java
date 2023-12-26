package com.musicfileutil.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 主程序入口
 *
 * <p>通过实现 {@code ApplicationListener} 接口来使 {@code spring} 组件注册完成后
 * 再初始化生成窗口
 *
 * @author Yi Chen
 * @date 2023-12-10 21:08
 * @since 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.musicfileutil"})
public class UiApplication implements ApplicationListener<ContextRefreshedEvent> {
    private UIWindow window;

    public UiApplication() {
    }

    @Autowired
    public UiApplication(UIWindow window) {
        this.window = window;
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(UiApplication.class).headless(false)
                                                         .run(args);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (null == event.getApplicationContext()
                         .getParent()) {
            window.init();
        }
    }
}
