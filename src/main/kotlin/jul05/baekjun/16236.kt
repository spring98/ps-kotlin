package jul05.baekjun

import java.util.PriorityQueue

data class Shark(val x: Int, val y: Int, val size: Int, val ate: Int, val cost: Int)
data class Fish(val x: Int, val y: Int, val size: Int)
fun main() {
    //더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청한다.
    //먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
    //먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
    //거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야하는 칸의 개수의 최솟값이다.
    //거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
    //아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다. 예를 들어, 크기가 2인 아기 상어는 물고기를 2마리 먹으면 크기가 3이 된다.

//    0: 빈 칸
//    1, 2, 3, 4, 5, 6: 칸에 있는 물고기의 크기
//    9: 아기 상어의 위치

    val n = 4
    val graph = mutableListOf(
        mutableListOf(4, 3, 2, 1),
        mutableListOf(0, 0, 0, 0),
        mutableListOf(0, 0, 9, 0),
        mutableListOf(1, 2, 3, 4),
    )

    val fishes = mutableListOf<Fish>()
//    val fishes = PriorityQueue<Fish>(compareByDescending { it.size })
    lateinit var shark: Shark

    for (y in 0 until n) {
        for (x in 0 until n) {
            val size = graph[y][x]

            // 상어 일 때
            if (size == 9) {
                shark = Shark(x, y,2,0,0)
            }

            // 물고기 일 때
            else {
                fishes.add(Fish(x, y, size))
            }
        }
    }

    val dx = listOf(0,-1,0,1)
    val dy = listOf(-1,0,1,0)

    fun bfs(startX: Int, startY: Int): Int {
        val needVisit = ArrayDeque<Shark>()
        needVisit.add(Shark(startX, startY, 0, 0))
        val visited = Array(n) { BooleanArray(n) }

        while (needVisit.isNotEmpty()) {
            val shark = needVisit.removeFirst()

            if (!visited[shark.y][shark.x]) {
                visited[shark.y][shark.x] = true

                for (i in 0 until 4) {
                    val nx = shark.x + dx[i]
                    val ny = shark.y + dy[i]

                    if (nx in 0 until n && ny in 0 until n && !visited[ny][nx] ){

                    }
                }
            }

        }
    }

    val eatableFishes = fishes.filter { it.size < shark.size }
    val candidates = PriorityQueue(compareBy<Triple<Int, Int, Int>> { it.first }
        .thenByDescending { it.second }
        .thenByDescending { it.third })

    while (eatableFishes.isNotEmpty()) {
        val eatableFish = eatableFishes.removeLast()
        val x = eatableFish.x
        val y = eatableFish.y
        val distance = bfs(x, y)

        candidates.add(Triple(distance, y, x))
    }

    val eatenFish = candidates.poll()
    val eatenX = eatenFish.third
    val eatenY = eatenFish.second
    graph[eatenY][eatenX] = 0






    bfs(2,2)

}



