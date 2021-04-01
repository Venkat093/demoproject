package login.auth.Controller;

import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import login.auth.Models.AuthenticationRequest;
import login.auth.Models.UserModel;
import login.auth.Service.UserRepository;

@RestController
public class homeController {

    @Autowired
    AuthenticationManager authenticationManager;

    
    @Autowired
    private UserRepository repository;

    @PostMapping("/login")
    public ResponseEntity<?> signIn(@RequestBody AuthenticationRequest auth) {

        String username = auth.getUsername();
        String password = auth.getPassword();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            return ResponseEntity.ok("Authentication Failed! " + e);
        }
        return ResponseEntity.ok("Authentication Succesful! " + username);
    }

    @PostMapping("/adduser")
    public ResponseEntity<?> signUp(@RequestBody UserModel user) {

        repository.save(user);
        return ResponseEntity.ok("Added User to the Database with id: " + user.getId());

    }

    @GetMapping("/allUsers")
    public List<UserModel> getAllUsers() {
        return repository.findAll();
    }

    @GetMapping("/allUsers/{id}")
    public Optional<UserModel> getUser(@PathVariable int id) {
        return repository.findById(id);
    }

    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) throws Exception {
        repository.deleteById(id);
        return ResponseEntity.ok("deleted User to the Database with id: " + id);
    }
}
