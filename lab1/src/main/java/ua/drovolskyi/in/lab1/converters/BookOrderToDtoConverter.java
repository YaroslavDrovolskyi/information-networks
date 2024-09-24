package ua.drovolskyi.in.lab1.converters;

import org.springframework.core.convert.converter.Converter;
import ua.drovolskyi.in.lab1.dto.BookDto;
import ua.drovolskyi.in.lab1.dto.BookOrderDto;
import ua.drovolskyi.in.lab1.entities.Book;
import ua.drovolskyi.in.lab1.entities.BookOrder;

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
        dto.setCustomerId(source.getCustomer() != null ? source.getCustomer().getId() : null);
        dto.setStatus(source.getStatus());
        return dto;
    }
}
