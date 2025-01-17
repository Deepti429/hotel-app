package hotel.controller;

import hotel.entity.AppUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/api/v1/country")
@RestController
public class CountryController {
    @PostMapping("/addCountry")
    public AppUser addCountry(
            @AuthenticationPrincipal AppUser user
            )
    {
        return user;
    }
}
