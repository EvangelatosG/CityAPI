package com.example.cityAPI.api;

import com.example.cityAPI.model.ApiCall;
import com.example.cityAPI.model.City;
import com.example.cityAPI.service.ApiCallService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.DataInput;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiCallRestController {

    ApiCallService apiCallService;
    ObjectMapper mapper;
    ApiCall apiCall;

    @Autowired
    public ApiCallRestController(ApiCallService apiCallService, ObjectMapper mapper, ApiCall apiCall){
        this.apiCallService = apiCallService;
        this.mapper = mapper;
        this.apiCall = apiCall;

    }

    @GetMapping("/{city}/{state}/{country}")
    public City save(@PathVariable String city, @PathVariable String state, @PathVariable String country ){


        try {

            //  URLs cannot have spaces. Spaces are replaced with "%20" to be valid when we call a Web API .
            city = city.replaceAll(" ", "%20");
            state = state.replaceAll(" ", "%20");
            country = country.replaceAll(" ", "%20");

            String weatherURL = "http://api.airvisual.com/v2/city?city="+ city +"&state="+ state +"&country="+ country +"&key=4070fdd8-e3fc-40de-8c5e-2c366434b28b";

            //Create URI from String
            URI uri = URI.create(weatherURL);
            //Convert URI to URL
            URL url = uri.toURL();

            // Map JSON from the given URL to City Object
            City cityObject = mapper.readValue(url, City.class);

            apiCall.setCity(cityObject);
            apiCall.setId(0);

            //Persist the API Call to the Database
            apiCallService.save(apiCall);

                return cityObject;


        }catch(MalformedURLException urlException){
            urlException.getMessage();
            City cityObject = new City();
            cityObject.setStatus("fail");
            return cityObject;
        }catch (IOException ioException){
            ioException.getMessage();    //return null;
            City cityObject = new City();
            cityObject.setStatus("fail");
            return cityObject;
        }catch (Exception exception){
            exception.getMessage();
            City cityObject = new City();
            cityObject.setStatus("fail");
            return cityObject;
        }

    }

    /*-----------------------------------------------*/
    // CRUD Methods. Local Data - Oracle Database
    /*-----------------------------------------------*/

    @GetMapping("/localdata/apicalls")
    public List<ApiCall> localFindAll(){
        return apiCallService.localFindAll();
    }

    @GetMapping("/localdata/apicalls/{apiCallId}")
    public ApiCall localFindById(@PathVariable int apiCallId){

        ApiCall apiCall = apiCallService.localFindById(apiCallId);

        if(apiCall == null){
            throw new RuntimeException("ApiCall not found - " + apiCallId);
        }
            return apiCall;

    }

    @DeleteMapping("/localdata/apicalls/{apiCallId}")
    public String localDeleteById(@PathVariable int apiCallId){

        ApiCall apiCall = apiCallService.localFindById(apiCallId);

        if(apiCall == null){
            throw new RuntimeException("ApiCall not found - " + apiCallId);
        }

        apiCallService.localDeleteById(apiCallId);

        return "Deleted ApiCall id " + apiCallId;

    }

    @PutMapping("/localdata/apicalls")
    public ApiCall localUpdateApiCall(@RequestBody ApiCall apiCall){
        apiCallService.save(apiCall);
        return apiCall;
    }

    @PostMapping("/localdata/apicalls")
    public ApiCall localAddApiCall(@RequestBody ApiCall apiCall){
        apiCall.setId(0);
        apiCallService.save(apiCall);
        return apiCall;
    }


}
