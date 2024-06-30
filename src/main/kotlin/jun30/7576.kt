package jun30

fun main() = with(System.`in`.bufferedReader()) {
    val xy = readLine().split(" ").map { it.toInt() }
    val xLength = xy[0]
    val yLength = xy[1]

    val graph = Array(yLength) { IntArray(xLength) }
    for (i in 0 until yLength) {
        graph[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    val dx = listOf(1, 0, -1, 0)
    val dy = listOf(0, 1, 0, -1)

    var answer = 0
    fun bfs(candidates: MutableList<Triple<Int, Int, Int>>) {
        val needVisit = ArrayDeque<Triple<Int, Int, Int>>()
        needVisit.addAll(candidates)

        val visited = Array(yLength) { BooleanArray(xLength) }
        var localCost = 0

        while (needVisit.isNotEmpty()) {
            val (x, y, cost) = needVisit.removeFirst()

            if (!visited[y][x]) {
                visited[y][x] = true
                graph[y][x] = 1
                localCost = cost

                for (i in 0 until 4) {
                    val nx = x + dx[i]
                    val ny = y + dy[i]

                    if (nx in 0 until xLength && ny in 0 until yLength && !visited[ny][nx] && graph[ny][nx] != -1) {
                        needVisit.add(Triple(nx, ny, cost + 1))
                    }
                }
            }
        }

        answer += localCost
    }

    val candidates = mutableListOf<Triple<Int, Int, Int>>()
    for (i in 0 until yLength) {
        for (j in 0 until xLength) {
            if (graph[i][j] == 1) {
                candidates.add(Triple(j, i, 0))
            }
        }
    }

    bfs(candidates)

    for (row in graph) {
        for (r in row) {
            if (r == 0) {
                answer = -1
            }
        }
    }

    println(answer)
}