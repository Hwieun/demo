package com.github.prgrms.review;

import com.github.prgrms.orders.Review;
import com.github.prgrms.orders.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JdbcReviewRepositoryTest {

    @Resource
    ReviewRepository reviewRepository;

    @Test
    public void save() throws Exception {
        // given
        Review review = Review.builder().content("junit test").userSeq(1L).productSeq(1L).build();

        // when
        Review save = reviewRepository.save(review);

        // then
        assertThat(save.getSeq()).isEqualTo(2L);
        assertThat(save.getContent()).isEqualTo(review.getContent());
    }

    @Test
    public void findByProductAndUser() throws Exception {
        // given
        Long user = 1L;
        Long product = 2L;

        // when
        Optional<Review> optional = reviewRepository.findByProductAndUser(product, user);

        // then
        assertNotNull(optional.get());
        assertThat(optional.get().getProductSeq()).isEqualTo(product);
        assertThat(optional.get().getUserSeq()).isEqualTo(user);
        assertThat(optional.get().getContent()).isEqualTo("I like it!");
    }


}