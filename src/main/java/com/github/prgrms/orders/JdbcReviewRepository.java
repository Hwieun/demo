package com.github.prgrms.orders;

import com.github.prgrms.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcReviewRepository implements ReviewRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcReviewRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Review save(Review review) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("reviews").usingGeneratedKeyColumns("seq");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("user_seq", review.getUserSeq());
        parameters.put("product_seq", review.getProductSeq());
        parameters.put("content", review.getContent());

        Long id = jdbcInsert.executeAndReturnKey(parameters).longValue();
        review.setSeq(id);

        return review;
    }

    @Override
    public Optional<Review> findByProductAndUser(Long productSeq, Long userSeq) {
        Review review = jdbcTemplate.queryForObject("select * from reviews where product_seq = ? and user_seq = ?", mapper
                , productSeq, userSeq);

        return Optional.of(review);
    }

    static RowMapper<Review> mapper = (rs, i) ->
            new Review(rs.getLong("seq"),
                    rs.getLong("user_seq"),
                    rs.getLong("product_seq"),
                    rs.getString("content"),
                    DateTimeUtils.dateTimeOf(rs.getTimestamp("create_at")));
}
