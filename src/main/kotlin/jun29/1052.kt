package jun29

/**
 * 틀림
 */
fun main() = with(System.`in`.bufferedReader()) {
    var answer = 0
    val input = readLine().split(" ").map { it.toInt() }
    val n = input[0]
    val k = input[1]

    val bottles = mutableMapOf<Int, Int>()

    // Pair > 리터, 개수
    bottles[1] = n

    while (true) {
        // 값 기준으로 최대 값을 가진 키-값 쌍 찾기
        val maxEntry = bottles.maxBy { it.value }
        val (litter, num) = maxEntry

//        println("$bottles, $litter, $num")

        if (num == 1) {
            bottles[1] = bottles.getOrDefault(1, 0) + 1
            answer += 1
            continue
        }

        if (num % 2 == 1) {
            bottles[litter] = num % 2
        } else {
            bottles.remove(litter)
        }

        bottles[litter*2] = bottles.getOrDefault(litter * 2, 0) + num / 2

        if (bottles.values.sum() <= k) {
            break
        }

    }

    println(answer)
}