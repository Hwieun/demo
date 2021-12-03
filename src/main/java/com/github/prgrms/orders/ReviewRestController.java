package com.github.prgrms.orders;

import com.github.prgrms.security.JwtAuthentication;
import com.github.prgrms.utils.ApiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.github.prgrms.utils.ApiUtils.*;

@RestController
@RequestMapping("api/orders")
public class ReviewRestController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/{id}/review")
    public ApiUtils.ApiResult<ReviewDto> createReview(@AuthenticationPrincipal JwtAuthentication authentication, @RequestBody String body, @PathVariable Long id) {
        Review save = reviewService.save(body, authentication.id, id);
        return success(new ReviewDto(save));
    }
}