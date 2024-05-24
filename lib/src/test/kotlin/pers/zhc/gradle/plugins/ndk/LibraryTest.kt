package pers.zhc.gradle.plugins.ndk

import pers.zhc.android.def.AndroidAbi
import pers.zhc.android.def.AndroidTarget
import pers.zhc.android.def.BuildType
import java.io.File
import kotlin.test.Test
import kotlin.test.assertSame

class LibraryTest {
    @Test
    fun parseTargetString() {
        val target = "x86_64-29"
        val value = ConfigParser.BuildTarget.parse(target)
        assert(value.abi == "x86_64" && value.api == 29)

        val result = runCatching {
            println(ConfigParser.BuildTarget.parse("x862_64-29"))
        }
        assert(result.isFailure)
    }

    @Test
    fun parseTomlFile() {
        val tomlFile = File("src/test/resources/test1.toml")
        val config = ConfigParser.parse(tomlFile)
        println(config)

        assertSame(config.ndk.buildType, BuildType.Release)
        assert(
            config.ndk.targets == listOf(
                AndroidTarget(AndroidAbi.ARM_V8, 21),
                AndroidTarget(AndroidAbi.X86_64, 29),
            )
        )

        assert(
            GradleExtensionConfigConverters.targetsToMap(config.ndk.targets)
                .toString() == "[{abi=arm64-v8a, api=21}, {abi=x86_64, api=29}]"
        )
    }
}
