package com.example.web_ai_back.gps;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@NoArgsConstructor
@Setter
public class Gps {

    private double longitude;
    private double latitude;

    public Gps(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
}