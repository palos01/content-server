package ai.dnai.cs.client;

import ai.dnai.cs.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class MutationClient extends Client {

    private static final Logger log = LoggerFactory.getLogger(MutationClient.class);

    @Autowired
    private AppConfig conf;

    @Value("classpath:imageProps.template")
    private Resource imagePropsTemplate;

    /*
    public Image createImage(ImageProperties data) {
        log.info("Creating image in annotator " + data);

        ClientHttpRequestFactory client = getClientHttpRequestFactory();
        RestTemplate rest = new RestTemplate(client);
        Image image = rest.postForObject(conf.getAnnotationServiceURL() + "/createImage", new CreateImageRequest(data), Image.class);
        return image;
    }
    */

    public String createImage(ImageProperties props, String aiRecognizedFeatures) throws IOException {
        ClientHttpRequestFactory client = getClientHttpRequestFactory();

        String createImageRequestBody = concatReqBody(props, aiRecognizedFeatures);

        RestTemplate rest = new RestTemplate(client);
        ResponseEntity<String> response = rest.postForEntity(conf.getAnnotationServiceURL(), createImageRequestBody, String.class);

        return response.getBody();
    }

    public String concatReqBody(ImageProperties props, String aiRecognizedFeatures) throws IOException {
        String template = readAllLines(this.imagePropsTemplate.getInputStream());
        template = template.replace("$path", props.getImagePath());
        template = template.replace("$name", props.getImageName());
        template = template.replace("$height", String.valueOf(props.getImageHeight()));
        template = template.replace("$width", String.valueOf(props.getImageWidth()));

        int beginIndex = aiRecognizedFeatures.indexOf("\"properties\":");
        int endIndex = aiRecognizedFeatures.indexOf("}", beginIndex);

        return aiRecognizedFeatures.substring(0, beginIndex) + "\"properties\":" + template + aiRecognizedFeatures.substring(endIndex + 1);
    }

    public static String readAllLines(InputStream is) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            StringBuffer result = new StringBuffer();
            for (;;) {
                String line = reader.readLine();
                if (line == null)
                    break;
                result.append(line);
                result.append("\n");
            }
            return result.toString();
        }
    }

}