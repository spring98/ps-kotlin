package utils

fun <T> permutationsWithRepetition(elements: List<T>, k: Int): List<List<T>> {
    val result = mutableListOf<List<T>>()
    val current = mutableListOf<T>()

    fun generate() {
        if (current.size == k) {
            result.add(current.toList())
            return
        }
        for (element in elements) {
            current.add(element)
            generate()
            current.removeAt(current.size - 1)
        }
    }

    generate()
    return result
}

