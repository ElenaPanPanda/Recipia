package core.common.string_res_provider

import androidx.annotation.ArrayRes
import androidx.annotation.StringRes

interface StringResProvider {
    fun getString(@StringRes stringResId: Int): String

    fun getString(@StringRes stringResId: Int, vararg params: Any): String

    fun getStringArray(@ArrayRes stringResId: Int): List<String>
}