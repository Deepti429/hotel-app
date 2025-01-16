package hotel.controller;

import hotel.repository.CityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cities")
public class CityController {
    private CityRepository cityRepository;

    public CityController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }
    @DeleteMapping("/record/{id}")
    public ResponseEntity<String>deleteCity(
            @PathVariable Long id
    ){
        return cityRepository.findById(id)
                .map(city -> { cityRepository.delete(city);
                    return ResponseEntity.ok("City deleted.");
                }) .orElse(ResponseEntity.notFound().build());
    }
}
