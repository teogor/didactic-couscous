package dev.teogor.ceres.plugin.util

import java.security.MessageDigest
import org.gradle.api.Project

internal fun Project.safeProp(key: String): String? = if (hasProperty(key)) {
  property(key).toString()
} else {
  null
}

internal fun String.toMD5(): String {
  val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
  return bytes.toHex()
}

internal fun ByteArray.toHex(): String {
  return joinToString("") { "%02x".format(it) }
}
