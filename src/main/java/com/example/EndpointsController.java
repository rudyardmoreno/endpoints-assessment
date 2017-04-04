package com.example;

/*
 * Created by Rudyard Moreno on 3/30/17
 * DISH NETWORK - Galvanize Training
 * CNE-002 (Dish)
 * Endpoints Controller
 */

import com.example.model.Camelize;
import com.example.model.Encode;
import com.example.model.Redact;
import com.example.model.Sed;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
public class EndpointsController {

    //Endpoint 1 - Camelize
    @GetMapping("/camelize")
    public String camelize(Camelize camelize) {
        return camelize.getCamelize();
    }

    //Endpoint 2 - Redact
    @GetMapping("/redact")
    public String redact(@RequestParam MultiValueMap<String, String> querystring) {
        String result="";
        Redact redact = new Redact();
        for (String key : querystring.keySet()) {
            if (key.equals("original")) {
                for (String value : querystring.get(key)){
                    redact.setOriginal(value);
                }
            }
            if (key.equals("badWord")) {
                redact.setBadWords(querystring.get(key));
            }
        }
        result=redact.getRedact();
        return result;
    }

    //Endpoint 3 - Encode
    @PostMapping(value="/encode", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String encode(Encode encode) {
        return encode.getEncode();
    }

    //Endpoint 4 - Sed
    @PostMapping("/s/{find}/{replace}")
    public String sed(@PathVariable String find, @PathVariable String replace, @RequestBody String rawBody) {
        Sed sed = new Sed();
        sed.setFind(find);
        sed.setReplace(replace);
        sed.setMessage(rawBody);
        return sed.getSed();
    }
}
