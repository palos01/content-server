package ai.dnai.cs.api.rest;


import ai.dnai.cs.config.AppConfig;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;

@RestController
@RequestMapping(value = "/content/image")
@Api(value = "hotels", description = "Image API")
public class ImageController extends AbstractRestHandler {

    private static final Logger log = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private AppConfig appConfig;

    @RequestMapping(value = "/stream/**", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a single image.", notes = "You have to provide a valid imageName.")
    public void getImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

        String imagePath = path.substring(path.indexOf("/stream") + "/stream".length());

        log.info("Serving image " + imagePath);
        FileInputStream fis = new FileInputStream(new File(appConfig.getProcessedDir() + "/" + imagePath));
        IOUtils.copy(fis, response.getOutputStream());
        fis.close();
    }

}
