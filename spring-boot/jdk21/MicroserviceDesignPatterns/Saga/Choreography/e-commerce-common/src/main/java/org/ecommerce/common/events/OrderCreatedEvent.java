package org.ecommerce.common.events;

public record OrderCreatedEvent(
        Integer orderId,
        String userId,
        Integer productId
) {}
