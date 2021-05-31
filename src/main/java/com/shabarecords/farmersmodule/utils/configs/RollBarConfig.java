package com.shabarecords.farmersmodule.utils.configs;

import com.rollbar.notifier.Rollbar;
import com.rollbar.notifier.config.Config;
import com.rollbar.notifier.config.ConfigBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Odinga David
 * @since : 5/25/21, Tue
 */
@Configuration()
public class RollBarConfig {

    /**
     * Register a Rollbar bean to configure App with Rollbar.
     */
    @Bean
    public Rollbar rollbar() {
        return new Rollbar(getRollBarConfigs());
    }

    private Config getRollBarConfigs() {
        // Reference ConfigBuilder.java for all the properties you can set for Rollbar
        return  ConfigBuilder.withAccessToken("1081b7f6f10e454b8467b794096b0e91")
                .environment("development")
                .build();
    }
}

