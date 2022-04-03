package hn.edu.ujcv.pdm_2022_i_i3_2020110228

actual class Platform actual constructor() {
    actual val platform: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}