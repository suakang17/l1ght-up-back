package com.example.web_ai_back.gps;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Gps {

    private float longitude;
    private float latitude;

    public Gps(float longitude, float latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
