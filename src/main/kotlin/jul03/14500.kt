package jul03

/**
 * 하나 하나 구현 하는 것 보다 dfs + backtracking 으로 4개 길이만 찾는 것이 좋음
 * ㅗ 모양은 4개의 길이가 안됨 3개 길이 짜리임 그래서 예외처리가 필요함
 */
import kotlin.math.*
fun main() = with(System.`in`.bufferedReader()) {
    val (yLength, xLength) = readLine().split(" ").map { it.toInt() }
    val graph = Array(yLength) { IntArray(xLength) }

    for (i in 0 until yLength) {
        graph[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    var answer = 0

    fun run(x: Int, y: Int) {
        // 1 번: 2개
        if (x+3 < xLength) answer = max(answer, graph[y][x]+graph[y][x+1]+graph[y][x+2]+graph[y][x+3])
        if (y+3 < yLength) answer = max(answer, graph[y][x]+graph[y+1][x]+graph[y+2][x]+graph[y+3][x])

        // 2번: 1개
        if (x+1 < xLength && y+1 < yLength) {
            answer = max(answer, graph[y][x]+graph[y][x+1]+graph[y+1][x]+graph[y+1][x+1])
        }

        // 3번: 4개
        if (x+1 < xLength && y+2 < yLength) answer = max(answer, graph[y][x]+graph[y+1][x]+graph[y+2][x]+graph[y+2][x+1])
        if (x+2 < xLength && 0 <= y-1) answer = max(answer, graph[y][x]+graph[y][x+1]+graph[y][x+2]+graph[y-1][x+2])
        if (0 <= x-1  && 0 <= y-2) answer = max(answer, graph[y][x]+graph[y-1][x]+graph[y-2][x]+graph[y-2][x-1])
        if (0 <= x-2  && y+1 < yLength) answer = max(answer, graph[y][x]+graph[y+1][x]+graph[y+1][x-1]+graph[y+1][x-2])

        // 3번 대칭
        if (0 <= x-1 && y+2 < yLength) answer = max(answer, graph[y][x]+graph[y+1][x]+graph[y+2][x]+graph[y+2][x-1])
        if (0 <= x-2 && 0 <= y-1) answer = max(answer, graph[y][x]+graph[y][x-1]+graph[y][x-2]+graph[y-1][x-2])
        if (x+1 < xLength  && 0 <= y-2) answer = max(answer, graph[y][x]+graph[y-1][x]+graph[y-2][x]+graph[y-2][x+1])
        if (x+2 < xLength  && y+1 < yLength) answer = max(answer, graph[y][x]+graph[y+1][x]+graph[y+1][x+1]+graph[y+1][x+2])

        // 4번: 4개
        if (x+1 < xLength && y+2 < yLength) answer = max(answer, graph[y][x]+graph[y+1][x]+graph[y+1][x+1]+graph[y+2][x+1])
        if (x+2 < xLength && 0 <= y-1) answer = max(answer, graph[y][x]+graph[y][x+1]+graph[y-1][x+1]+graph[y-1][x+2])
        if (0 <= x-1 && 0 <= y-2) answer = max(answer, graph[y][x]+graph[y-1][x]+graph[y-1][x-1]+graph[y-2][x-1])
        if (0 <= x-2 && y+1 < yLength) answer = max(answer, graph[y][x]+graph[y][x-1]+graph[y+1][x-1]+graph[y+1][x-2])

        // 4번 대칭
        if (0 <= x-1 && y+2 < yLength) answer = max(answer, graph[y][x]+graph[y+1][x]+graph[y+1][x-1]+graph[y+2][x-1])
        if (0 <= x-2 && 0 <= y-1) answer = max(answer, graph[y][x]+graph[y][x-1]+graph[y-1][x-1]+graph[y-1][x-2])
        if (x+1 < xLength && 0 <= y-2) answer = max(answer, graph[y][x]+graph[y-1][x]+graph[y-1][x+1]+graph[y-2][x+1])
        if (x+2 < xLength && y+1 < yLength) answer = max(answer, graph[y][x]+graph[y][x+1]+graph[y+1][x+1]+graph[y+1][x+2])

        // 5번: 4개
        if (0 <= x-1 && x+1 < xLength && y+1 < yLength) answer = max(answer, graph[y][x]+graph[y+1][x]+graph[y+1][x-1]+graph[y+1][x+1])
        if (0 <= y-1 && y+1 < yLength && x+1 < xLength) answer = max(answer, graph[y][x]+graph[y][x+1]+graph[y-1][x+1]+graph[y+1][x+1])
        if (0 <= x-1 && x+1 < xLength && 0 <= y-1) answer = max(answer, graph[y][x]+graph[y-1][x]+graph[y-1][x-1]+graph[y-1][x+1])
        if (0 <= x-1 && 0 <= y-1 && y+1 < yLength) answer = max(answer, graph[y][x]+graph[y][x-1]+graph[y-1][x-1]+graph[y+1][x-1])
    }

    for (i in 0 until yLength) {
        for (j in 0 until xLength) {
            run(j, i)
        }
    }

    println(answer)
}