package com.skynet2.demo.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.boot.convert.DurationUnit;


import java.time.Duration;
import java.time.temporal.ChronoUnit;

@ConfigurationProperties("wait")
@ConstructorBinding
public class WaitProperties {

    private Duration forTime;

    // Duration unit, if not specified by application properties so with @DurationUnit annotation
    // I 'm setting a default
    // in this case I cannot use for keyword since it's a reserved word so i can use @Name annotation
    public WaitProperties(@DefaultValue("0") @DurationUnit(ChronoUnit.SECONDS) @Name("for") Duration forTime) {
        this.forTime = forTime;
    }

    public Duration getFor() {
        return forTime;
    }
}
