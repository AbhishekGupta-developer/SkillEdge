package com.myorganisation.SkillEdge.service;

import com.myorganisation.SkillEdge.dto.request.UserRequestDto;
import com.myorganisation.SkillEdge.dto.response.UserResponseDto;
import com.myorganisation.SkillEdge.exception.UserNotFoundException;
import com.myorganisation.SkillEdge.model.User;
import com.myorganisation.SkillEdge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setUsername(userRequestDto.getUsername());
        user.setPassword(userRequestDto.getPassword());

        userRepository.save(user);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setUsername(user.getUsername());

        return userResponseDto;
    }

    @Override
    public UserResponseDto getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException("User id: " + id + " doesn't exist")
                );
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setUsername(user.getUsername());

        return userResponseDto;
    }
}
