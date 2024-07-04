package jul04

import kotlin.math.*

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

data class Point(val x: Int, val y: Int)
fun main() = with(System.`in`.bufferedReader()){
    var answer = 0

    val (yLength, xLength) = readLine().split(" ").map { it.toInt() }
    val graph = Array(yLength) { IntArray(xLength) }
    for (i in 0 until yLength) {
        graph[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    val zeroPoint = mutableListOf<Point>()
    val startPoint = mutableListOf<Point>()

    for (y in 0 until yLength) {
        for (x in 0 until xLength) {
            if (graph[y][x] == 0) {
                zeroPoint.add(Point(x, y))
            }

            if (graph[y][x] == 2) {
                startPoint.add(Point(x, y))
            }
        }
    }

    val dx = listOf(1,0,-1,0)
    val dy = listOf(0,1,0,-1)

    for (combination in combination(zeroPoint, 3)) {
        var localAnswer = 0
        val copiedGraph = graph.map { it.toMutableList() }.toMutableList()

        combination.forEach {
            copiedGraph[it.y][it.x] = 1
        }

        // bfs
        val needVisit = ArrayDeque<Point>()
        needVisit.addAll(startPoint)
        val visited = Array(yLength) { BooleanArray(xLength) }

        while (needVisit.isNotEmpty()) {
            val point = needVisit.removeFirst()

            if (!visited[point.y][point.x]) {
                visited[point.y][point.x] = true
                copiedGraph[point.y][point.x] = 2

                for (i in 0 until 4) {
                    val nx = point.x + dx[i]
                    val ny = point.y + dy[i]

                    if (nx in 0 until xLength && ny in 0 until yLength && !visited[ny][nx] && copiedGraph[ny][nx] == 0) {
                        needVisit.add(Point(nx, ny))
                    }
                }
            }
        }

        for (y in 0 until yLength) {
            for (x in 0 until xLength) {
                if (copiedGraph[y][x] == 0) {
                    localAnswer += 1
                }
            }
        }

        answer = max(answer, localAnswer)
    }

    println(answer)
}