package test.provider;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class OkResponseEntityProvider implements ResponseEntityProvider<String> {

    @Override
    public ResponseEntity<String> provide() {
        return ResponseEntity.ok("pong");
    }
}
