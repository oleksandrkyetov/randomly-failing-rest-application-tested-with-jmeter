package test;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void greetingShouldReturnDefaultMessage() {
        Assertions.assertThat(restTemplate.getForEntity("http://localhost:" + port + "/", String.class).getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(restTemplate.getForEntity("http://localhost:" + port + "/", String.class).getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(restTemplate.getForEntity("http://localhost:" + port + "/", String.class).getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(restTemplate.getForEntity("http://localhost:" + port + "/", String.class).getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(restTemplate.getForEntity("http://localhost:" + port + "/", String.class).getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
