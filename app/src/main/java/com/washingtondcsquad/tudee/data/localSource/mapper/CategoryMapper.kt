package com.washingtondcsquad.tudee.data.localSource.mapper

import com.washingtondcsquad.tudee.data.localSource.model.CategoryEntity
import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.entity.CategoryID
import com.washingtondcsquad.tudee.domain.entity.ImageSource
import com.washingtondcsquad.tudee.domain.provider.StringProvider
import org.koin.java.KoinJavaComponent.getKoin

fun Category.toEntity(): CategoryEntity {
    return CategoryEntity(
        id = CategoryID(0L), title = title, image = when (this.iconPath) {
            is ImageSource.AddedByUser -> (this.iconPath as ImageSource.AddedByUser).value
            is ImageSource.PredefinedDrawable -> (this.iconPath as ImageSource.PredefinedDrawable).id.toString()
        }, isPredefined = this.isPredefined
    )
}

fun CategoryEntity.toDomain(
    stringProvider: StringProvider = getKoin().get<StringProvider>(),
): Category {
    return Category(
        id = id,
        title = if (isPredefined) {
            stringProvider.getString(this.title.toInt())
        } else this.title,
        iconPath = if (isPredefined) {
            ImageSource.PredefinedDrawable(image.toInt())
        } else {
            ImageSource.AddedByUser(image)
        },
        isPredefined = isPredefined
    )
}