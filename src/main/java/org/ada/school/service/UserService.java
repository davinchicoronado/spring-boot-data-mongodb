package org.ada.school.service;

import org.ada.school.dto.UserDto;
import org.ada.school.model.User;
import org.ada.school.repository.UserDocument;

import java.util.List;
import java.util.Optional;

public interface UserService
{
    User create( User user );

    User findById(String id );

    List<User> all();

    boolean deleteById( String id );

    User update( UserDto userDto, String id );
}
