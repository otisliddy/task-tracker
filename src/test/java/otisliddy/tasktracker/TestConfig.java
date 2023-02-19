package otisliddy.tasktracker;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.netty.http.client.HttpClient;

@TestConfiguration
public class TestConfig {

    @Value("${server.port}")
    private String serverPort;

    @Bean
    public WebTestClient init() throws Exception {
        SslContext sslContext = SslContextBuilder.forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

        HttpClient httpClient = HttpClient.create()
                .secure(t -> t.sslContext(sslContext));

        ReactorClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

        return WebTestClient.bindToServer(connector)
                .baseUrl("https://localhost:" + serverPort)
                .build();
    }
}
