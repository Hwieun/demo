package com.github.prgrms.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
@Transactional
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review save(String content, Long userSeq, Long productSeq) {
        checkNotNull(content, "Review content must be provided");
        Review review = Review.builder().content(content)
                .userSeq(userSeq).productSeq(productSeq).build();
        return reviewRepository.save(review);
    }

}
