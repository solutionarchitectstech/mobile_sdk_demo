package tech.solutionarchitects.testapplication.bannermodel

import android.os.Parcelable
import tech.solutionarchitects.advertisingsdk.core.model.Size
import tech.solutionarchitects.advertisingsdk.types.CloseButtonType
import kotlinx.parcelize.Parcelize

@Parcelize
data class BannerModel(
    val refresh: Int,
    val timeout: Int,
    val placementId: Int,
    val closeButtonType: CloseButtonType,
    val sizes: List<Size>
) : Parcelable