package org.ecommerce.common.events;

import org.ecommerce.common.model.Order;
import org.ecommerce.common.model.User;

public record OrderPaymentCompleteEvent(
        Order order,
        User user,
        String paymentId
) {
}
