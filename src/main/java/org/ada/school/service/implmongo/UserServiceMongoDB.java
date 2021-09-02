package org.ada.school.service.implmongo;

import org.ada.school.dto.UserDto;
import org.ada.school.model.User;
import org.ada.school.repository.UserDocument;
import org.ada.school.repository.UserRepository;
import org.ada.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceMongoDB implements UserService {

    private final UserRepository userRepository;

    public UserServiceMongoDB(@Autowired UserRepository userRepository )
    {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user )
    {
        userRepository.save(new UserDocument(user));
        return user;
    }

    @Override
    public User findById(String id )
    {
        if(userRepository.existsById(id)){
            return new User(userRepository.findById(id).get());
        }
        return null;
    }

    @Override
    public List<User> all()
    {
        List<User> users = new ArrayList<>();
        List<UserDocument> userDocuments = userRepository.findAll();

        for (UserDocument user:userDocuments){
            users.add(new User(user));
        }
        return users;
    }

    @Override
    public boolean deleteById( String id )
    {
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public User update(UserDto userDto, String id )
    {
        if(userRepository.existsById(id)){
            User user= new User(userRepository.findById(id).get());
            user.update(userDto);
            userRepository.insert(new UserDocument(user));
            return user;
        }
        return null;
    }
}
