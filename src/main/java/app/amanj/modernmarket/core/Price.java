package app.amanj.modernmarket.core;

import lombok.Data;

@Data
public class Price{

    String name;
    double price;
    String symbol;
    String updatedAt;
    String updatedAtReadable;

}