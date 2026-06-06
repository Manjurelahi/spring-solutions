package org.ecommerce.common.events;

import org.ecommerce.common.model.Order;
import org.ecommerce.common.model.User;

public record OrderPaymentFailedEvent(
        Order order,
        User userId,
        String paymentId
) {
}
