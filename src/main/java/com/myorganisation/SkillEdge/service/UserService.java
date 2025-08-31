package com.myorganisation.SkillEdge.service;

import com.myorganisation.SkillEdge.dto.request.UserRequestDto;
import com.myorganisation.SkillEdge.dto.response.UserResponseDto;

public interface UserService {
    UserResponseDto registerUser(UserRequestDto userRequestDto);
    UserResponseDto getUser(Long id);
}
