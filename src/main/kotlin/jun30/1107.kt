package jun30

import kotlin.math.*

/**
 * 기본적인 아이디어에는 문제가 없었다.
 * permutation 의 범위를 잡는 부분에서 이슈가 있었다. (반례 찾기)
 */
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

fun main() = with(System.`in`.bufferedReader()){
    val buttons = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

    val target = readLine().toInt()
    val n = readLine().toInt()
    var notWorkButtons = mutableListOf<Int>()

    when (n) {
        0 -> {

        }
        1 -> {
            notWorkButtons.add(readLine().toInt())
        }
        else -> {
            notWorkButtons = readLine().split(" ").map { it.toInt() }.toMutableList()
        }
    }

    if (target == 100) {
        println(0)
        System.exit(0)
    }

    notWorkButtons.forEach { buttons.remove(it) }
    if (buttons.isEmpty()) {
        println(abs(100 - target))
        System.exit(0)
    }

    var answer = Int.MAX_VALUE
    var step2 = Int.MAX_VALUE
    for (i in 1 .. target.toString().length) {
        val permutations = permutationsWithRepetition(buttons, i)

        permutations.forEach {
            step2 = min(step2, abs(target - it.joinToString(separator = "").toInt()))
            answer = min(answer, i + step2)
        }
    }

    println(min(answer, abs(100 - target)))
}
