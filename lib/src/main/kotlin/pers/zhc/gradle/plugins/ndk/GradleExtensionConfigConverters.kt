package pers.zhc.gradle.plugins.ndk

import pers.zhc.android.def.AndroidTarget
import java.io.Serializable

object GradleExtensionConfigConverters {
    private fun AndroidTarget.toMap(): Map<String, Serializable> {
        return mapOf(Pair("abi", this.abi.toString()), Pair("api", this.api))
    }

    fun targetsToMap(targets: List<AndroidTarget>): List<Map<String, Serializable>> {
        return targets.map { it.toMap() }
    }
}
