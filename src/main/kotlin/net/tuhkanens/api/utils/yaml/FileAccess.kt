package net.tuhkanens.api.utils.yaml

data class FileAccess(
    val reader: FileReader,
    val writer: FileWriter
)