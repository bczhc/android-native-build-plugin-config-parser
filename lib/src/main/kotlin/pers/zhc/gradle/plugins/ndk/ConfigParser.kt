package pers.zhc.gradle.plugins.ndk

import org.gradle.api.GradleException
import org.tomlj.Toml
import java.io.File

object ConfigParser {
    fun parse(configFile: File) {
        val toml = Toml.parse(configFile.reader())
        if (toml.hasErrors()) {
            for (error in toml.errors()) {
                System.err.println("Toml parsing error: $error")
            }
            throw GradleException("Toml parser has errors.")
        }


    }

    data class BuildTarget(
        val abi: String,
        val api: Int,
    ) {
        companion object {
            private const val HELP_MSG = """Format: <ABI-name>-<Android-API-version>
Example:
build_targets = ["arm64-v8a-29", "x86_64-29"]"""

            fun parse(targetString: String): BuildTarget {
                if (!targetString.matches(Regex("^.*-[0-9]+\$"))) {
                    throw GradleException("Wrong NDK target format: $targetString\n$HELP_MSG")
                }

                val found = Regex("^(.*)-([0-9]+)\$").find(targetString)!!
                val groupValues = found.groupValues
                val abi = groupValues[1]
                val api = groupValues[2].toInt()

                runCatching {
                    AndroidAbi.from(abi)
                }.onFailure {
                    throw GradleException("Invalid ABI name: $abi")
                }

                return BuildTarget(
                    abi = abi, api = api
                )
            }
        }
    }
}
