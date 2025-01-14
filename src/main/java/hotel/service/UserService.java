package hotel.service;

import hotel.entity.AppUser;
import hotel.repository.AppUserRepository;
import hotel.repository.LoginDto;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private AppUserRepository appUserRepository;
    private JWTService jwtService;

    public UserService(AppUserRepository appUserRepository, JWTService jwtService) {
        this.appUserRepository = appUserRepository;
        this.jwtService = jwtService;
    }

    public String verifylogin(LoginDto dto){
        Optional<AppUser> opUser = appUserRepository.findByUsername(dto.getUsername());
        if(opUser.isPresent()){
            AppUser appUser = opUser.get();
           if (BCrypt.checkpw(dto.getPassword(),appUser.getPassword())){
               String token = jwtService.generateToken(appUser.getUsername());
               return token;
           }
        }else{
            return null;
        }
        return null;
    }
}
