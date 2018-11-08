package ai.dnai.cs.api.rest;

import ai.dnai.cs.service.ServiceProperties;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by palos on 8/15/17.
 */
@RestController
@RequestMapping(value = "/status")
@Api(value = "status", description = "Application Status API")
public class EnvironmentController extends AbstractRestHandler {


    @Autowired
    private ServiceProperties serviceProperties;

    @RequestMapping(value = "/env",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get env properties", notes = "System and custom properties are returned.")
    public
    @ResponseBody
    Map getStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {

        return serviceProperties.getOverallStatus();
    }


}
