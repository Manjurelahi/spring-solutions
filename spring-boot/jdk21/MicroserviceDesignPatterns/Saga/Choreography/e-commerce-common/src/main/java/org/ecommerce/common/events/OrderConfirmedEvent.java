package org.ecommerce.common.events;

import org.ecommerce.common.model.Order;
import org.ecommerce.common.model.User;

public record OrderConfirmedEvent(
        Order order,
        User user
) {
}
