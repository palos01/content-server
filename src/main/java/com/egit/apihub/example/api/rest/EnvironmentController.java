package com.egit.apihub.example.api.rest;

import com.egit.apihub.example.domain.Hotel;
import com.egit.apihub.example.service.ServiceProperties;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
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
    @ApiOperation(value = "Get a single hotel.", notes = "You have to provide a valid city name.")
    public
    @ResponseBody
    Map getStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {

        return serviceProperties.getOverallStatus();
    }


}
