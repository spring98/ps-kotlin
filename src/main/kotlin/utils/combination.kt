package utils

fun <T> combination(elements: List<T>, r: Int): List<List<T>> {
    val n = elements.size
    val results = mutableListOf<List<T>>() // 모든 경우의 수
    val result = MutableList(r) { elements[0] } // 크기를 고정한 MutableList

    fun recursionCombination(depth: Int = 0, index: Int = 0) {
        if (depth == r) {
            results.add(result.toList())
            return
        }

        for (i in index until n) {
            result[depth] = elements[i]
            recursionCombination(depth + 1, i + 1)
        }
    }

    recursionCombination()
    return results
}
