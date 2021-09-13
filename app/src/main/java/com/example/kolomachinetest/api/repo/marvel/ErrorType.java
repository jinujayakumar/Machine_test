package com.example.kolomachinetest.api.repo.marvel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

@IntDef({ErrorType.TYPE_EMPTY, ErrorType.TYPE_UNKNOWN})
@Retention(RetentionPolicy.SOURCE)
public @interface ErrorType {
    int TYPE_EMPTY = 1;
    int TYPE_UNKNOWN = 2;
}
