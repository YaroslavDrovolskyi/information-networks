package ua.drovolskyi.in.lab1.converters;


import org.springframework.core.convert.converter.Converter;
import ua.drovolskyi.in.lab1.dto.UserDto;
import ua.drovolskyi.in.lab1.entities.User;

public class UserToDtoConverter implements Converter<User, UserDto> {
    @Override
    public UserDto convert(User source) {
        if(source == null){
            return null;
        }

        UserDto dto = new UserDto();
        dto.setId(source.getId());
        dto.setLogin(source.getLogin());
        dto.setRole(source.getRole());
        dto.setName(source.getName());
        dto.setSurname(source.getSurname());
        dto.setPatronymic(source.getPatronymic());
        dto.setPhoneNumber(source.getPhoneNumber());
        dto.setIsAllowedToLogin(source.getIsAllowedToLogin());

        return dto;
    }
}
