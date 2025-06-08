package com.example.nobsv2.nobsv2.product.headers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeaderController {

    @GetMapping("/header")
    public String getRegionalResponse(@RequestHeader(required = false, defaultValue = "US") String region) {
        if (region.equals("US"))
            return "MAKE AMERICA GREAT AGAIN";
        if (region.equals("CA"))
            return "INDIA V2";

        return "Country not supported";
    }
}
