package com.example.datastore

import androidx.datastore.core.Serializer
import java.io.InputStream
import java.io.OutputStream

object ShoppingListSerializer : Serializer<ShoppingList> {
    override val defaultValue: ShoppingList = ShoppingList.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): ShoppingList {
        return ShoppingList.parseFrom(input)
    }

    override suspend fun writeTo(t: ShoppingList, output: OutputStream) {
        t.writeTo(output)
    }
}