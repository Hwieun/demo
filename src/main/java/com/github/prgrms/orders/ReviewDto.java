package com.github.prgrms.orders;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.prgrms.products.Product;

import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

public class ReviewDto {
    private Long seq;

    @JsonProperty("productId")
    private Long productSeq;

    private String content;

    private LocalDateTime createAt;

    public ReviewDto(Review source) {
        copyProperties(source, this);
    }

}
