package com.github.prgrms.orders;

import java.util.List;

public interface OrderRepository {

    public List<Order> findAllByUserId(Long userId);

    public Order findByOrderId(Long orderId);

    public int update(Order order);

}
