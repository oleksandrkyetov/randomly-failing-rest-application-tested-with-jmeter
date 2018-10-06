package test.provider;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class BadRequestResponseEntityProvider implements ResponseEntityProvider<String> {

    @Override
    public ResponseEntity<String> provide() {
        return ResponseEntity.badRequest().build();
    }
}
