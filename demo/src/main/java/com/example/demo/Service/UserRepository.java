package login.auth.Service;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import login.auth.Models.*;

public interface UserRepository extends MongoRepository<UserModel, Integer> {

    Optional<UserModel> findByUserName(String username);

}
