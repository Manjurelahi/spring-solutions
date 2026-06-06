package org.ecommerce.common.events;

public record OrderOutOfStockEvent(
        Integer orderId,
        Integer productId
) {
}
