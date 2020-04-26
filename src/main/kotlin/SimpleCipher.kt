data class Cipher(val key: String = getRandomKey()) {

    init {
        require(key.isNotBlank())
        require(key.all { it.isLowerCase() })
    }

    fun encode(s: String): String =
            s.mapIndexed {
                index, c -> 'a' + ((c - 'a') + (key[index % key.length] - 'a')).floorMod(26)
            }.joinToString("")

    fun decode(s: String): String =
            s.mapIndexed {
                index, c -> 'a' + ((c - 'a') - (key[index % key.length] - 'a')).floorMod(26)
            }.joinToString("")

    companion object {
        private fun getRandomKey(): String =
                generateSequence { ('a'..'z').random() }.take(100).joinToString("")

        private fun Int.floorMod(y: Int) =
                Math.floorMod(this, y)
    }
}
