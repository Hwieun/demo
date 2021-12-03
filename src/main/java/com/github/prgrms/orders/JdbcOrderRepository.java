package com.github.prgrms.orders;

import com.github.prgrms.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcOrderRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Order> findAllByUserId(Long userId) {
        return jdbcTemplate.query("select * from orders where user_seq = ?", mapper, userId);
    }

    @Override
    public Order findByOrderId(Long orderId) {
        return jdbcTemplate.queryForObject("select * from orders from seq = ?", mapper, orderId);
    }

    @Override
    public int update(Order order) {
        return jdbcTemplate.update("update orders set review_seq = ?, state = ?, request_msg = ?, reject_msg = ?, completed_at = ?, rejected_at = ? " +
                "where seq = ?", order.getReviewSeq(), order.getState(), order.getRequestMsg(), order.getRejectMsg(), order.getCompletedAt(), order.getRejectedAt(), order.getSeq());
    }

    static RowMapper<Order> mapper = (rs, i) ->
            Order.builder()
                    .seq(rs.getLong("seq"))
                    .userSeq(rs.getLong("user_seq"))
                    .productSeq(rs.getLong("product_seq"))
                    .reviewSeq(rs.getLong("review_seq"))
                    .state(Order.State.valueOf(rs.getString("state")))
                    .requestMsg(rs.getString("request_msg"))
                    .rejectMsg(rs.getString("reject_msg"))
                    .completedAt(DateTimeUtils.dateTimeOf(rs.getTimestamp("completed_at")))
                    .rejectedAt(DateTimeUtils.dateTimeOf(rs.getTimestamp("rejected_at")))
                    .createAt(DateTimeUtils.dateTimeOf(rs.getTimestamp("create_at")))
                    .build();
}
