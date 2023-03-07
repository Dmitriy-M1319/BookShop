package ru.vsu.cs.tech.bookshop.mappers;

import org.mapstruct.Mapper;
import ru.vsu.cs.tech.bookshop.dto.BookGetDto;
import ru.vsu.cs.tech.bookshop.models.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookGetDto toGetDto(Book model);
    Book toModel(BookGetDto getDto);
}
