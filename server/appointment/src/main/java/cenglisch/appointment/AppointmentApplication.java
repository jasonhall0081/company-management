package cenglisch.appointment;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.config.server.EnableConfigServer;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableConfigServer
@EnableConfigurationProperties
public class AppointmentApplication extends SpringBootServletInitializer {

    private static final String CONFIG_FILE_PROPERTY = "spring.cloud.bootstrap.name";

    private static final String BOOTSTRAP_CONFIG_FILE_NAME = "appointment-bootstrap";

    public static void main(final String[] args) {
        new SpringApplicationBuilder(AppointmentApplication.class)
                .properties(CONFIG_FILE_PROPERTY + ":" + BOOTSTRAP_CONFIG_FILE_NAME)
                .build()
                .run(args);
    }

    protected final SpringApplicationBuilder configure(final SpringApplicationBuilder builder) {
        final Map<String, Object> props = new HashMap<>();
        props.put(CONFIG_FILE_PROPERTY, BOOTSTRAP_CONFIG_FILE_NAME);
        builder.properties(props);
        return builder.sources(AppointmentApplication.class);
    }
}
