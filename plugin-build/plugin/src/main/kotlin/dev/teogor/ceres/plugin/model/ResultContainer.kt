package dev.teogor.ceres.plugin.model

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import java.io.File
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Calendar

data class ResultContainer(
  var githubBranchName: String? = null,
) {
  val metadata: MetaData = MetaData()
}

class MetaData(
  val generated: String = DateTimeFormatter.ISO_DATE_TIME
    .withZone(ZoneOffset.UTC)
    .format(Calendar.getInstance().toInstant()),
)

fun ResultContainer.writeToDisk(
  outputFile: File,
) {
  val metadata = this.metadata
  val formattedDate = DateTimeFormatter.ISO_DATE_TIME
    .withZone(ZoneOffset.UTC)
    .format(Calendar.getInstance().toInstant())

  val packageName = "dev.teogor.ceres.plugin.info"

  val ceresInfoFile = FileSpec.builder(packageName, "CeresInfo")
    .addFunction(
      FunSpec.builder("getGithubBranchName")
        .addModifiers(KModifier.PUBLIC)
        .returns(String::class)
        .addStatement("return %S", githubBranchName ?: "")
        .build()
    )
    .addFunction(
      FunSpec.builder("getGeneratedDate")
        .addModifiers(KModifier.PUBLIC)
        .returns(String::class)
        .addStatement("return %S", formattedDate)
        .build()
    )
    .addType(metadataType(metadata))
    .build()

  val outputPath = outputFile.absolutePath
  ceresInfoFile.writeTo(outputFile)
}

private fun metadataType(metadata: MetaData): TypeSpec {
  return TypeSpec.classBuilder("MetaData")
    .addProperty(
      PropertySpec.builder("generated", String::class)
        .addModifiers(KModifier.PRIVATE)
        .initializer("%S", metadata.generated)
        .build()
    )
    .build()
}

