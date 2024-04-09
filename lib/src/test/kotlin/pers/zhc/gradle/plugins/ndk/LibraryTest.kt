package pers.zhc.gradle.plugins.ndk

import kotlin.test.Test

class LibraryTest {
    @Test fun parseTargetString() {
        val target = "x86_64-29"
        val value = ConfigParser.BuildTarget.parse("x86_64-29")
        assert(value.abi == "x86_64" && value.api == 29)

        val result = runCatching {
            println(ConfigParser.BuildTarget.parse("x862_64-29"))
        }
        assert(result.isFailure)
    }
}
