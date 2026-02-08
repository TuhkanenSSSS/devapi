package net.tuhkanens.api.utils.yaml

object FilesManager {

    private val files: MutableMap<String, FileData> = mutableMapOf()

    fun newFiles(vararg filesData: FileData) {

        val factory = FileFactory()

        for (fileData in filesData) {
            factory.newFile(fileData)
            files[fileData.fileName] = fileData
        }

    }

    fun getFileReader(fileName: String): FileReader {
        val fileData = files[fileName]
            ?: throw IllegalArgumentException("File '$fileName' not found for FileReader!")

        return FileReader(fileData)
    }

    fun getFileWriter(fileName: String): FileWriter {
        val fileData = files[fileName]
            ?: throw IllegalArgumentException("File '$fileName' not found for FileWriter!")

        return FileWriter(fileData)
    }

}