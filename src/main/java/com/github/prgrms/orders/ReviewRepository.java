package com.github.prgrms.orders;

import java.util.Optional;

public interface ReviewRepository {

    public Review save(Review review);

    public Optional<Review> findByProductAndUser(Long productSeq, Long userSeq);
}
