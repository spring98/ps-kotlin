package jun29

fun main() = with(System.`in`.bufferedReader()) {
    val t = readLine().toInt()

    for (j in 0 until t) {
        val n = readLine()!!.toInt()
        val start = readLine()!!.split(" ").map { it.toInt() }
        val end = readLine()!!.split(" ").map { it.toInt() }

        val startX = start[0]
        val startY = start[1]
        val endX = end[0]
        val endY = end[1]

        val dx = listOf(2,1,2,1,-1,-2,-1,-2)
        val dy = listOf(1,2,-1,-2,2,1,-2,-1)

        fun bfs(): Int {
             val needVisit = ArrayDeque<Triple<Int, Int, Int>>()
            needVisit.add(Triple(startX, startY, 0))
            val visited = Array(n+1) { BooleanArray(n+1)}

            while (needVisit.isNotEmpty()) {
                val (x, y, cost) = needVisit.removeFirst()

                if (x == endX && y == endY) {
                    return cost
                }

                if (x in 0 until n && y in 0 until n && !visited[y][x]) {
                    visited[y][x] = true
                    for (i in 0..7) {
                        val nx = x + dx[i]
                        val ny = y + dy[i]

                        needVisit.add(Triple(nx, ny, cost+1))
                    }
                }
            }

            return 0
        }

        println(bfs())
    }
}
