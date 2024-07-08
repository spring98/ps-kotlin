package jul08

fun main() = with(System.`in`.bufferedReader()){
    val (yLength, xLength, time) = readLine().split(" ").map { it.toInt() }

    val graph = Array(yLength) { IntArray(xLength) }
    for (i in 0 until yLength) {
        graph[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    val cleaner = mutableListOf<Int>()
    for (y in 0 until yLength) {
        for (x in 0 until xLength) {
            if (graph[y][x] == -1) {
                cleaner.add(y)
            }
        }
    }

    val (cleanerTop, cleanerBottom) = cleaner

    val dx = listOf(1,0,-1,0)
    val dy = listOf(0,1,0,-1)

    fun diffuse() {
        val temp = Array(yLength) { IntArray(xLength) }
        for (y in 0 until yLength) {
            for (x in 0 until xLength) {
                val dust = graph[y][x]
                val sideDust = dust / 5
                if (dust == 0 || dust == -1) continue

                for (i in 0 until 4) {
                    val nx = x + dx[i]
                    val ny = y + dy[i]

                    if (nx in 0 until xLength && ny in 0 until yLength && graph[ny][nx] != -1) {
                        temp[ny][nx] += sideDust
                        temp[y][x] -= sideDust
                    }
                }
            }
        }

        for (y in 0 until yLength) {
            for (x in 0 until xLength) {
                graph[y][x] += temp[y][x]
            }
        }
    }

    fun clean() {
        graph[cleanerTop-1][0] = 0
        graph[cleanerBottom+1][0] = 0

        // 순서1
        for (y in cleanerTop-1 downTo 1) {
            graph[y][0] = graph[y-1][0]
        }
        graph[0][0]

        // 순서2
        for (x in 0 until xLength-1) {
            graph[0][x] = graph[0][x+1]
        }
        graph[0][xLength-1] = 0

        // 순서3
        for (y in 0 until cleanerTop) {
            graph[y][xLength-1] = graph[y+1][xLength-1]
        }
        graph[cleanerTop][xLength-1] = 0

        // 순서4
        for (x in xLength-2 downTo 1) {
            graph[cleanerTop][x+1] = graph[cleanerTop][x]
        }
        graph[cleanerTop][1] = 0

        // 순서5
        for (y in cleanerBottom+1 until yLength-1) {
            graph[y][0] = graph[y+1][0]
        }
        graph[yLength-1][0] = 0

        // 순서6
        for (x in 0 until xLength-1) {
            graph[yLength-1][x] = graph[yLength-1][x+1]
        }
        graph[yLength-1][xLength-1] = 0

        // 순서7
        for (y in yLength-1 downTo cleanerBottom+1) {
            graph[y][xLength-1] = graph[y-1][xLength-1]
        }
        graph[cleanerBottom][xLength-1] = 0

        // 순서8
        for (x in xLength-1 downTo 2) {
            graph[cleanerBottom][x] = graph[cleanerBottom][x-1]
        }
        graph[cleanerBottom][1] = 0
    }

    for (ii in 0 until time ) {
        diffuse()
        clean()
    }

    var answer = 0
    for (y in 0 until yLength) {
        for (x in 0 until xLength) {
            val value = graph[y][x]
            if (value == 0 || value == -1) continue

            answer += value
        }
    }

    println(answer)
}