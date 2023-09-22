package org.binarfud.model;

import lombok.*;

@Getter
@Setter
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    private int id;
    private Product product;
    private int qty;
    private double total;

    @Override
    public String toString() {
        return String.format("%s\t\t %d\t\t %.2f", product.getName(), qty, total);
    }
}
