package ua.drovolskyi.in.lab2.lab2backend.converters;

import org.springframework.core.convert.converter.Converter;
import ua.drovolskyi.in.lab2.lab2backend.dto.BookOrderDto;
import ua.drovolskyi.in.lab2.lab2backend.entities.BookOrder;
import ua.drovolskyi.in.lab2.lab2backend.repositories.BookRepository;
import ua.drovolskyi.in.lab2.lab2backend.repositories.UserRepository;

public class BookOrderToDtoConverter
        implements Converter<BookOrder, BookOrderDto> {

    @Override
    public BookOrderDto convert(BookOrder source) {
        if(source == null){
            return null;
        }

        BookOrderDto dto = new BookOrderDto();
        dto.setId(source.getId());
        dto.setBookId(source.getBook() != null ? source.getBook().getId() : null);
        dto.setBookTitle(source.getBook() != null ? source.getBook().getTitle() : null);
        dto.setCustomerId(source.getCustomer() != null ? source.getCustomer().getId() : null);
        dto.setCustomerUsername(source.getCustomer() != null ? source.getCustomer().getUsername() : null);
        dto.setStatus(source.getStatus());

        return dto;
    }
}