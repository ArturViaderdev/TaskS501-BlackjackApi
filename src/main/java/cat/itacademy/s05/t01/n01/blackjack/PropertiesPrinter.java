package cat.itacademy.s05.t01.n01.blackjack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PropertiesPrinter implements CommandLineRunner {

    @Value("${spring.r2dbc.url}")
    private String r2dbcUrl;

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Value("${spring.r2dbc.username}")
    private String r2dbcUser;

    @Value("${spring.r2dbc.password}")
    private String r2dbcPassword;

    @Override
    public void run(String... args) {
        System.out.println("=== VALORES DE PROPERTIES AL ARRANCAR ===");
        System.out.println("spring.r2dbc.url: " + r2dbcUrl);
        System.out.println("spring.r2dbc.username: " + r2dbcUser);
        System.out.println("spring.r2dbc.password: " + r2dbcPassword);
        System.out.println("spring.data.mongodb.uri: " + mongoUri);
        System.out.println("=========================================");
    }
}
