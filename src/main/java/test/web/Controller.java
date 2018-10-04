package test.web;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import test.provider.ResponseEntityProvider;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;

@RestController
public class Controller {

    @Autowired
    private ResponseEntityProvider<String> okResponseEntityProvider;

    @Autowired
    private ResponseEntityProvider<String> badRequestResponseEntityProvider;

    @Autowired
    private ResponseEntityProvider<String> notFoundResponseEntityProvider;

    private List<ResponseEntityProvider<String>> responseEntityProviders;

    @PostConstruct
    public void init() {
        responseEntityProviders = ImmutableList.of(
            okResponseEntityProvider,
            okResponseEntityProvider,
            badRequestResponseEntityProvider,
            notFoundResponseEntityProvider,
            okResponseEntityProvider,
            okResponseEntityProvider,
            badRequestResponseEntityProvider,
            okResponseEntityProvider,
            notFoundResponseEntityProvider,
            badRequestResponseEntityProvider,
            okResponseEntityProvider,
            okResponseEntityProvider,
            badRequestResponseEntityProvider,
            okResponseEntityProvider
        );
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ping")
    public ResponseEntity<String> ping() {
        try {
            // Emulate latency, 3000 ms max
            Thread.sleep(new Random().nextInt(3000));
        } catch (InterruptedException ie) { }

        // Pick response
        return responseEntityProviders.get(new Random().nextInt(responseEntityProviders.size())).provide();
    }
}
