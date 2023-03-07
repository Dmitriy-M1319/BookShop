package ru.vsu.cs.tech.bookshop.mappers;

import org.mapstruct.Mapper;
import ru.vsu.cs.tech.bookshop.dto.OrderDto;
import ru.vsu.cs.tech.bookshop.models.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(Order model);
    Order toModel(OrderDto dto);
}
