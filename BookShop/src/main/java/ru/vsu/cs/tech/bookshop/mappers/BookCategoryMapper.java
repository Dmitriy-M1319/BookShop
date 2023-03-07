package ru.vsu.cs.tech.bookshop.mappers;

import org.mapstruct.Mapper;
import ru.vsu.cs.tech.bookshop.dto.BookCategoryDto;
import ru.vsu.cs.tech.bookshop.models.BookCategory;

@Mapper(componentModel = "spring")
public interface BookCategoryMapper {
    BookCategory toModel(BookCategoryDto dto);
    BookCategoryDto toDto(BookCategory model);
}
