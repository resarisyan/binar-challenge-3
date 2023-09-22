package org.binarfud.model;

import lombok.*;

@Getter
@Setter
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private int id;
    private String name;
    private double price;

    @Override
    public String toString() {
        return String.format("%d. %s\t | %.2f", id, name, price);
    }
}
