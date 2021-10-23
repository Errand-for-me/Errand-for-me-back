package com.errand.project.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrandType {

    Delivery("배달"),
    Driving("운전"),
    Teaching("과외"),
    Cleaning("청소"),
    ETC("기타");

    private final String description;

}
