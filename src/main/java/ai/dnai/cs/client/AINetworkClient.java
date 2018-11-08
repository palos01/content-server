package ai.dnai.cs.client;

import ai.dnai.cs.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class AINetworkClient extends Client {

    private static final Logger log = LoggerFactory.getLogger(AINetworkClient.class);

    @Autowired
    private AppConfig conf;

    private HttpComponentsClientHttpRequestFactory httpFactory;

    public String idnetifyImageFatures(Path pathToImage) throws IOException {
        MultiValueMap<String, Object> parts =
                new LinkedMultiValueMap<String, Object>();
        parts.add("image", new ByteArrayResource(Files.readAllBytes(pathToImage)));
        parts.add("capture", "camera");

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity =
                new HttpEntity<MultiValueMap<String, Object>>(parts, headers);

        ResponseEntity<String> response =
                restTemplate.exchange(conf.getAINetworkURL(),
                        HttpMethod.POST, requestEntity, String.class);

        return response.getBody();
    }

}
