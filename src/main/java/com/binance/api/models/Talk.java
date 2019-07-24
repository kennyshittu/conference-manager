package com.binance.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Talk {
    private int minutes;
    private String title;
}
