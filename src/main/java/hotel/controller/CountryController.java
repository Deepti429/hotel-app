package hotel.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/api/v1/country")
@RestController
public class CountryController {
    @PostMapping("/addCountry")
    public String addCountry()
    {
        return "added";
    }
}
