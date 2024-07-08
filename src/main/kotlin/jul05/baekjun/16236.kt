package jul05.baekjun

/**
 * 1. 초기에 시작하는 상어 위치를 0으로 만들어줘야함
 * 2. 먹을 수 있는 모든 먹이에 대해 bfs 를 하는 것이 아니라
 *      bfs 로 전체를 뒤져 먹을 수 있는 애들을 Priority 로 관리해야한다. TIME_OUT
 */
import java.util.PriorityQueue
data class Shark(var x: Int, var y: Int, var size: Int, var ate: Int, var time: Int)
data class Fish(val x: Int, val y: Int, val size: Int)
data class Candidate(val x: Int, val y: Int, val distance: Int, val fish: Fish)

fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()
    val graph = Array(n) { IntArray(n) }
    for (i in 0 until n) {
        graph[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    val fishes = mutableListOf<Fish>()
    lateinit var shark: Shark

    // 400
    for (y in 0 until n) {
        for (x in 0 until n) {
            val size = graph[y][x]

            // 상어 일 때
            if (size == 9) {
                shark = Shark(x, y, 2, 0, 0)
                graph[y][x] = 0
            }

            // 물고기 일 때
            else if (size != 0){
                fishes.add(Fish(x, y, size))
            }
        }
    }

    val dx = listOf(1,0,-1,0)
    val dy = listOf(0,1,0,-1)

    // 400
    fun bfs(endX: Int, endY: Int): Int {
        val needVisit = ArrayDeque<Shark>()
        needVisit.add(shark.copy(time = 0))
        val visited = Array(n) { BooleanArray(n) }

        while (needVisit.isNotEmpty()) {
            val s = needVisit.removeFirst()

            if (s.x == endX && s.y == endY) {
                return s.time
            }

            if (!visited[s.y][s.x]) {
                visited[s.y][s.x] = true

                for (i in 0 until 4) {
                    val nx = s.x + dx[i]
                    val ny = s.y + dy[i]

                    // 상어의 크기보다 작거나 같은 물고기 위치는 지나갈 수 있긴함
                    if (nx in 0 until n && ny in 0 until n && !visited[ny][nx] && graph[ny][nx] <= s.size) {
                        needVisit.add(s.copy(x = nx, y = ny, time = s.time + 1))
                    }
                }
            }
        }

        return Int.MAX_VALUE
    }

    // 400
    while (true) {
        // 400
        val eatableFishes = fishes.filter { it.size < shark.size }

        if (eatableFishes.isEmpty()) {
            break
        }

        val candidates = PriorityQueue(compareBy<Candidate> { it.distance }
            .thenBy { it.y }
            .thenBy { it.x })

        // 400 * 400 = 16만
        for (eatableFish in eatableFishes) {
//            val eatableFish = eatableFishes.removeLast()
            val x = eatableFish.x
            val y = eatableFish.y
            val distance = bfs(x, y)

            if (distance != Int.MAX_VALUE) {
                candidates.add(Candidate(x, y, distance, eatableFish))
            }
        }

        if (candidates.isNotEmpty()) {
            val eatenFish = candidates.poll()

            fishes.remove(eatenFish.fish)
            graph[eatenFish.y][eatenFish.x] = 0
            shark.time += eatenFish.distance
            shark.x = eatenFish.x
            shark.y = eatenFish.y
            shark.ate += 1
            if (shark.size == shark.ate) {
                shark.size += 1
                shark.ate = 0
            }
        }
    }

    println(shark.time)
}
