package ua.drovolskyi.in.lab2.lab2backend.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import ua.drovolskyi.in.lab2.lab2backend.dto.BookDto;
import ua.drovolskyi.in.lab2.lab2backend.dto.PageDto;
import ua.drovolskyi.in.lab2.lab2backend.entities.Book;

import java.util.List;

// C is class of content, D is DTO-class
public class PageToDtoConverter<C,D> implements Converter<Page<C>, PageDto<D>> {
    private Converter<C,D> contentConverter; // must be set in constructor, or in setter

    public PageToDtoConverter(Converter<C, D> contentConverter) {
        this.contentConverter = contentConverter;
    }

    public void setContentConverter(Converter<C, D> contentConverter) {
        this.contentConverter = contentConverter;
    }


    // https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Page.html
    @Override
    public PageDto<D> convert(Page<C> source) {

        if(source == null){
            return null;
        }

        PageDto<D> dto = new PageDto<>();
        dto.setContent(
                source.getContent()
                .stream()
                .map(contentConverter::convert)
                .toList()
        );
        dto.setIndexOfPage(source.getNumber());
        dto.setTotalPages(source.getTotalPages());
        dto.setNumberOfElements(source.getNumberOfElements());
        dto.setTotalElements(source.getTotalElements());
        return dto;
    }
}
