package ua.drovolskyi.in.lab2.lab2backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PageDto<T>{
    private List<T> content; // hasContent()

    private Integer indexOfPage; // index of this page, zero-based // getNumber()
    private Integer totalPages; // total number of pages // getTotalPages()

    private Integer numberOfElements; // number of elements on this page
    private Long totalElements; // overall number of elements // getTotalElements()
}