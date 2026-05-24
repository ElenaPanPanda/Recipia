package com.example.recipia.core.domain.collections.mappers

import com.example.recipia.core.common.model.UserCollection
import com.example.recipia.core.network.dto.UserCollectionDto
import javax.inject.Inject

internal interface UserCollectionMapper {
    fun convert(response: UserCollectionDto): UserCollection
}

internal class UserCollectionMapperImpl @Inject constructor() :
    UserCollectionMapper {
    override fun convert(response: UserCollectionDto): UserCollection {
        with(response) {
            return UserCollection(
                collectionId = collectionId,
                collectionName = collectionName,
                recipes = recipes,
                collectionColor = collectionColor,
            )
        }
    }
}