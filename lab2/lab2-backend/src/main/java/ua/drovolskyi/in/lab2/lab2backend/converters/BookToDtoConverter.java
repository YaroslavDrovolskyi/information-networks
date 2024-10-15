package ua.drovolskyi.in.lab2.lab2backend.converters;


import org.springframework.core.convert.converter.Converter;
import ua.drovolskyi.in.lab2.lab2backend.dto.BookDto;
import ua.drovolskyi.in.lab2.lab2backend.entities.Book;

public class BookToDtoConverter
        implements Converter<Book, BookDto>{

    @Override
    public BookDto convert(Book source) {
        if(source == null){
            return null;
        }

        BookDto dto = new BookDto();
        dto.setId(source.getId());
        dto.setIsbn(source.getIsbn());
        dto.setTitle(source.getTitle());
        dto.setAuthors(source.getAuthors());
        dto.setNumberOfPages(source.getNumberOfPages());
        dto.setPublishingYear(source.getPublishingYear());
        dto.setQuantity(source.getQuantity());

        return dto;
    }
}