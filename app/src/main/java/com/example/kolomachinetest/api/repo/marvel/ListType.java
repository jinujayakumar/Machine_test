package com.example.kolomachinetest.api.repo.marvel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

@IntDef({ListType.TYPE_COMICS, ListType.TYPE_CHARACTERS, ListType.TYPE_SEARCH,
        ListType.TYPE_FILTER})
@Retention(RetentionPolicy.SOURCE)
public @interface ListType {
    int TYPE_COMICS = 1;
    int TYPE_CHARACTERS = 2;
    int TYPE_SEARCH = 3;
    int TYPE_FILTER = 4;
}
