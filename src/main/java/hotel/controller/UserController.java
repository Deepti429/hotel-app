package hotel.controller;

import hotel.entity.AppUser;
import hotel.payload.TokenDto;
import hotel.repository.AppUserRepository;
import hotel.repository.LoginDto;
import hotel.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/api/v1/users")
@RestController
public class UserController{
    private AppUserRepository appUserRepository;
 private UserService userService;
    public UserController(AppUserRepository appUserRepository, UserService userService) {
        this.appUserRepository = appUserRepository;
        this.userService = userService;
    }
    @PostMapping("/signup")
    public ResponseEntity<?>createUser(
            @RequestBody AppUser user
    ){
        Optional<AppUser> opUsername = appUserRepository.findByUsername(user.getUsername());
        if(opUsername.isPresent()){
            return new ResponseEntity<>("username already taken", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<AppUser> opEmail = appUserRepository.findByEmail(user.getEmail());
        if(opEmail.isPresent()){
            return new ResponseEntity<>("Email already taken", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String encryptedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(5));
        user.setPassword( encryptedPassword);
        user.setRole("ROLE_USER");
        AppUser savedUser = appUserRepository.save(user);
        return new ResponseEntity<>(savedUser,HttpStatus.CREATED);

    }
    @GetMapping("/message")
    public String getMessage(){

        return "Hello";
    }
    @PostMapping("/login")
    public ResponseEntity<?>login(
            @RequestBody LoginDto dto
    ){
        String token = userService.verifylogin(dto);
        if(token!=null){
            TokenDto tokenDto = new TokenDto();
            tokenDto.setToken(token);
            tokenDto.setType("JWT");
            return new ResponseEntity<>(tokenDto,HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Invalid password/username",HttpStatus.FORBIDDEN);

        }

    }

    @PostMapping("/signup-property-owner")
    public ResponseEntity<?>createPropertyOwnerUser(
            @RequestBody AppUser user
    ){
        Optional<AppUser> opUsername = appUserRepository.findByUsername(user.getUsername());
        if(opUsername.isPresent()){
            return new ResponseEntity<>("username already taken", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<AppUser> opEmail = appUserRepository.findByEmail(user.getEmail());
        if(opEmail.isPresent()){
            return new ResponseEntity<>("Email already taken", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String encryptedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(5));
        user.setPassword( encryptedPassword);
        user.setRole("ROLE_OWNER");
        AppUser savedUser = appUserRepository.save(user);
        return new ResponseEntity<>(savedUser,HttpStatus.CREATED);

    }


}


