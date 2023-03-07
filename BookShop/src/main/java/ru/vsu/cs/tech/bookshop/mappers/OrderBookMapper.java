package ru.vsu.cs.tech.bookshop.mappers;

import org.mapstruct.Mapper;
import ru.vsu.cs.tech.bookshop.dto.OrderBookGetDto;
import ru.vsu.cs.tech.bookshop.models.OrderBook;

@Mapper(componentModel = "spring")
public interface OrderBookMapper {
   OrderBookGetDto toDto(OrderBook model);
   OrderBook toModel(OrderBookGetDto dto);
}
