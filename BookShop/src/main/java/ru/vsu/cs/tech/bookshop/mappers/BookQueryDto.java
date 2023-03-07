package ru.vsu.cs.tech.bookshop.mappers;

import org.mapstruct.Mapper;
import ru.vsu.cs.tech.bookshop.dto.BookQueryGetDto;
import ru.vsu.cs.tech.bookshop.models.BooksQuery;

@Mapper(componentModel = "spring")
public interface BookQueryDto {
    BookQueryGetDto toDto(BooksQuery model);
    BooksQuery toModel (BookQueryGetDto dto);
}
