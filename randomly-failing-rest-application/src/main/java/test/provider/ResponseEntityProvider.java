package test.provider;

import org.springframework.http.ResponseEntity;

public interface ResponseEntityProvider<T> {

    ResponseEntity<T> provide();
}
