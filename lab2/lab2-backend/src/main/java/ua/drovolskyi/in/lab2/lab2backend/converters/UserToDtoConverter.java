package ua.drovolskyi.in.lab2.lab2backend.converters;

import org.springframework.core.convert.converter.Converter;
import ua.drovolskyi.in.lab2.lab2backend.dto.UserDto;
import ua.drovolskyi.in.lab2.lab2backend.entities.User;

public class UserToDtoConverter implements Converter<User, UserDto> {
    @Override
    public UserDto convert(User source) {
        if(source == null){
            return null;
        }

        UserDto dto = new UserDto();
        dto.setId(source.getId());
        dto.setUsername(source.getUsername());
        dto.setRole(source.getRole());
        dto.setFullName(source.getFullName());
        dto.setPhoneNumber(source.getPhoneNumber());
        dto.setIsAllowedToLogin(source.getIsAllowedToLogin());

        return dto;
    }
}