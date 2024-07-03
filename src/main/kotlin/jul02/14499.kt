package jul02

/**
 * 나는 십자가 모양만 고려하여 계산하였는데
 * Gpt 물어보니 6면체 모두 고려해서 계산해야한다고 함
 * 거의 다 맞았는데 아쉽다.
 */
//data class Dice(var c: Int, var l: Int, var r: Int, var t: Int, var b: Int)
//fun main() = with(System.`in`.bufferedReader()) {
//    val input = readLine()!!.split(" ").map { it.toInt() }
//    val yLength = input[0]
//    val xLength = input[1]
//    var x = input[2]
//    var y = input[3]
//    val k = input[4]
//
//    val graph = Array(yLength) { IntArray(xLength) }
//
//    for (i in 0 until yLength) {
//        graph[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
//    }
//
//    val commands = readLine().split(" ").map { it.toInt() }.toList()
//
//    /**
//     * 여기 부분 동적으로 계산하여 나타내게 변경하기
//     * 지금은 global coordinate 에서 봤을 때 정적상태임
//     */
//    val dice = IntArray(7)
//    var diceLocation = Dice(6, 4, 3, 2, 5)
//
//    fun run() {
//        for (command in commands) {
//            val c = diceLocation.c
//            val l = diceLocation.l
//            val r = diceLocation.r
//            val t = diceLocation.t
//            val b = diceLocation.b
//
//            when (command) {
//                1 -> x += 1
//                2 -> x -= 1
//                3 -> y -= 1
//                4 -> y += 1
//            }
//
//            // 만약에 범위 벗어나면 continue
//            if (x !in 0 until xLength || y !in 0 until yLength) {
//                when (command) {
//                    1 -> x -= 1
//                    2 -> x += 1
//                    3 -> y += 1
//                    4 -> y -= 1
//                }
//
//                continue
//            }
//
//            when (command) {
//                // 1 3 6 4 (동쪽)
//                // 1이 나오면 오른쪽 2가 나오면 왼쪽
//                1 -> diceLocation = diceLocation.copy(l = c, c = r, r = 7-c)
//                2 -> diceLocation = diceLocation.copy(l = 7-c, c = l, r = c)
//
//                // 1 5 6 2 (남쪽)
//                // 4가 나오면 오른쪽 3이 나오면 왼쪽
//                3 -> diceLocation = diceLocation.copy(t = 7-c, c = t, b = c)
//                4 -> diceLocation = diceLocation.copy(t = c, c = b, b = 7-c)
//            }
//
//            // 주사위 바닥이 0 이 아니면 graph 에 저장
//            if (graph[y][x] == 0) {
//                graph[y][x] = dice[diceLocation.c]
//            }
//
//            // graph 가 0 이 아니면 주사위 바닥 에 저장
//            else {
//                dice[diceLocation.c] = graph[y][x]
//                graph[y][x] = 0
//            }
//
//            println(dice[7-diceLocation.c])
//        }
//    }
//
//    run()
//}

data class Dice(var top: Int = 1, var bottom: Int = 6, var left: Int = 4, var right: Int = 3, var front: Int = 5, var back: Int = 2)

fun main() = with(System.`in`.bufferedReader()) {
    val input = readLine()!!.split(" ").map { it.toInt() }
    val yLength = input[0]
    val xLength = input[1]
    var y = input[2]
    var x = input[3]
    val k = input[4]

    val graph = Array(yLength) { IntArray(xLength) }

    for (i in 0 until yLength) {
        graph[i] = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    }

    val commands = readLine()!!.split(" ").map { it.toInt() }

    val dice = IntArray(7)
    var diceLocation = Dice()

    fun rollEast() {
        diceLocation = Dice(
            top = diceLocation.left,
            bottom = diceLocation.right,
            left = diceLocation.bottom,
            right = diceLocation.top,
            front = diceLocation.front,
            back = diceLocation.back
        )
    }

    fun rollWest() {
        diceLocation = Dice(
            top = diceLocation.right,
            bottom = diceLocation.left,
            left = diceLocation.top,
            right = diceLocation.bottom,
            front = diceLocation.front,
            back = diceLocation.back
        )
    }

    fun rollNorth() {
        diceLocation = Dice(
            top = diceLocation.front,
            bottom = diceLocation.back,
            left = diceLocation.left,
            right = diceLocation.right,
            front = diceLocation.bottom,
            back = diceLocation.top
        )
    }

    fun rollSouth() {
        diceLocation = Dice(
            top = diceLocation.back,
            bottom = diceLocation.front,
            left = diceLocation.left,
            right = diceLocation.right,
            front = diceLocation.top,
            back = diceLocation.bottom
        )
    }

    fun run() {
        for (command in commands) {
            when (command) {
                1 -> x += 1 // 동쪽
                2 -> x -= 1 // 서쪽
                3 -> y -= 1 // 북쪽
                4 -> y += 1 // 남쪽
            }

            // 만약 범위를 벗어나면 좌표 복구하고 continue
            if (x !in 0 until xLength || y !in 0 until yLength) {
                when (command) {
                    1 -> x -= 1
                    2 -> x += 1
                    3 -> y += 1
                    4 -> y -= 1
                }
                continue
            }

            when (command) {
                1 -> rollEast()
                2 -> rollWest()
                3 -> rollNorth()
                4 -> rollSouth()
            }

            // 주사위 바닥이 0이 아니면 그래프에 저장
            if (graph[y][x] == 0) {
                graph[y][x] = dice[diceLocation.bottom]
            } else {
                dice[diceLocation.bottom] = graph[y][x]
                graph[y][x] = 0
            }

            // 주사위 윗면 출력
            println(dice[diceLocation.top])
        }
    }

    run()
}