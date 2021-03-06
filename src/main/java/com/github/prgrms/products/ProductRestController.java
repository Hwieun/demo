package com.github.prgrms.products;

import com.github.prgrms.errors.NotFoundException;
import com.github.prgrms.utils.ApiUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("api/products")
public class ProductRestController {

  private final ProductService productService;

  public ProductRestController(ProductService productService) {
    this.productService = productService;
  }

  // FIXME `요건 1` 정의에 맞게 응답 타입 수정이 필요합니다.
  @GetMapping(path = "{id}")
  public ApiUtils.ApiResult<ProductDto> findById(@PathVariable Long id) {
    return productService.findById(id)
      .map(ProductDto::new).map(ApiUtils::success)
      .orElseThrow(() -> new NotFoundException("Could not found product for " + id));
  }

  // FIXME `요건 1` 정의에 맞게 응답 타입 수정이 필요합니다.
  @GetMapping
  public ApiUtils.ApiResult<List<ProductDto>> findAll() {
    return ApiUtils.success(productService.findAll().stream()
            .map(ProductDto::new)
            .collect(toList()));
  }

}