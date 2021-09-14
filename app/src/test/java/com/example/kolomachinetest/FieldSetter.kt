package com.example.kolomachinetest

import java.lang.reflect.Field

object FieldSetter {

    fun setField(clazz: Any, field: String, any: Any) {
        val f: Field = clazz.javaClass.getDeclaredField(field)
        f.isAccessible = true
        f.set(clazz, any)
    }
}