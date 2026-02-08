package net.tuhkanens.api.utils.yaml

object FilesManager {

    private val files: MutableMap<String, FileData> = mutableMapOf()
    private val readers: MutableMap<String, FileReader> = mutableMapOf()
    private val writers: MutableMap<String, FileWriter> = mutableMapOf()

    fun newFiles(vararg filesData: FileData) {

        val factory = FileFactory()

        for (fileData in filesData) {
            factory.newFile(fileData)

            files[fileData.fileName] = fileData
            readers[fileData.fileName] = FileReader(fileData)
            writers[fileData.fileName] = FileWriter(fileData)
        }

    }

    fun getFileReader(fileName: String): FileReader {
        return readers[fileName]
            ?: throw IllegalArgumentException("File '$fileName' not found for FileReader!")
    }

    fun getFileWriter(fileName: String): FileWriter {
        return writers[fileName]
            ?: throw IllegalArgumentException("File '$fileName' not found for FileWriter!")
    }

    fun reloadFile(fileName: String) {
        readers[fileName]?.reload()
            ?: throw IllegalArgumentException("File '$fileName' not found!")
        writers[fileName]?.reload()
    }

    fun reloadAll() {
        readers.values.forEach { it.reload() }
        writers.values.forEach { it.reload() }
    }

    fun hasFile(fileName: String): Boolean {
        return files.contains(fileName)
    }

    fun getFileNames(): Set<String> {
        return files.keys.toSet()
    }

}