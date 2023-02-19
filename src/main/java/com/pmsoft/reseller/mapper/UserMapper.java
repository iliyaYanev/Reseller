package com.pmsoft.reseller.mapper;

import com.pmsoft.reseller.model.dto.user.UserDto;
import com.pmsoft.reseller.model.dto.user.UserRegisterDto;
import com.pmsoft.reseller.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userRegisterDtoToUser(UserRegisterDto userRegisterDto);

    UserDto userToUserDto(User user);
}
