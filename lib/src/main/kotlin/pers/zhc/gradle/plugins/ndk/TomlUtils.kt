package pers.zhc.gradle.plugins.ndk

import org.gradle.api.GradleException
import org.tomlj.TomlArray
import org.tomlj.TomlParseResult

fun TomlArray.toStringList(): List<String> {
    return (0 until this.size()).map {
        this.getString(it)!!
    }
}

fun TomlParseResult.requireString(key: String): String {
    return this.getString(key) ?: throw GradleException("$key is required in `config.toml`")
}

fun TomlParseResult.requireArray(key: String): List<String> {
    return (this.getArray(key) ?: throw GradleException("$key is required in `config.toml`"))
        .toStringList()
}
