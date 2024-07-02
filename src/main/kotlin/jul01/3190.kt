package jul01

/**
 * directionPointer 의 index 가 음수가 되면
 * 나머지 연산을 할 수 없으므로 처리가 필요
 */
import java.util.PriorityQueue

data class Apple(val x: Int, val y: Int)
data class Turn(val second: Int, val direction: String)
data class Point(var x: Int, var y: Int)

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val k = readLine().toInt()
    val apples = mutableListOf<Apple>()

    for (i in 0 until k) {
        val (x, y) = readLine().split(" ").map { it.toInt() }
        apples.add(Apple(y-1, x-1))
    }

    val l = readLine().toInt()
    val turns = PriorityQueue<Turn>(compareBy { it.second })

    for (i in 0 until l) {
        val (s, d) = readLine().split(" ")
        turns.offer(Turn(s.toInt(), d))
    }

    val graph = Array(n+1) { IntArray(n+1) }
    graph[0][0] = 1

    for (apple in apples) {
        graph[apple.y][apple.x] = 2
    }

    fun run(): Int {
        val snake = ArrayDeque<Point>()
        var head = Point(0, 0)
        val directions = listOf("R", "D", "L", "U")
        var directionPointer = 0
        var time = 0
        snake.add(head)

        while (true) {
            if (turns.isNotEmpty()) {
                val turn = turns.peek()
                if (time == turn.second) {
                    if (turn.direction == "D") {
                        directionPointer += 1
                    }

                    if (turn.direction == "L") {
                        directionPointer -= 1
                    }

                    if (directionPointer < 0) {
                        directionPointer += 4
                    }

                    directionPointer %= 4
                    turns.poll()
                }
            }

            // 앞으로 한칸 가기
            when (directions[directionPointer]) {
                "R" -> head = head.copy(head.x + 1, head.y)
                "L" -> head = head.copy(head.x - 1, head.y)
                "U" -> head = head.copy(head.x, head.y - 1)
                "D" -> head = head.copy(head.x, head.y + 1)
            }

            // 만약 head 가 맵을 벗어난다면 게임 끝
            if (head.x !in 0 until n || head.y !in 0 until n) {
                break
            }

            // 만약 head 가 본인 몸통에 포함된다면 게임 끝
//            println(head.toString())
//            println(snake.toString())
            if (head in snake) {
                break
            }

            snake.addFirst(head)

            // 사과를 먹는다.
            if (graph[head.y][head.x] == 2) {
                graph[head.y][head.x] = 0
            }

            // 사과를 먹지 못했다면 꼬리 자르기
            else {
                snake.removeLast()
            }

            time += 1
        }

        return time
    }

    println(run()+1)
}