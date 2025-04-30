package core.common.string_res_provider

import android.content.Context
import androidx.annotation.ArrayRes
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StringResProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : StringResProvider {
    override fun getString(@StringRes stringResId: Int): String {
        return context.getString(stringResId)
    }

    override fun getString(@StringRes stringResId: Int, vararg params: Any): String {
        return context.getString(stringResId, *params)
    }

    override fun getStringArray(@ArrayRes stringResId: Int): List<String> {
        return context.resources.getStringArray(stringResId).toList()
    }
}