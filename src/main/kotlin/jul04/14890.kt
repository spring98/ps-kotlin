package jul04

/**
 * 아이디어는 맞은 거 같은데
 * 제대로 예외처리 못했다.
 */
fun main() {
    val n = 6
    val l = 2
    val graph = listOf(
        listOf(3, 3, 3, 3, 3, 3),
        listOf(2, 3, 3, 3, 3, 3),
        listOf(2, 2, 2, 3, 2, 3),
        listOf(1, 1, 1, 2, 2, 2),
        listOf(1, 1, 1, 3, 3, 1),
        listOf(1, 1, 2, 3, 3, 2),
    )
//    val graph = listOf(
//        listOf(3, 2, 1, 1, 2, 3),
//        listOf(3, 2, 2, 1, 2, 3),
//        listOf(3, 2, 2, 2, 3, 3),
//        listOf(3, 3, 3, 3, 3, 3),
//        listOf(3, 3, 3, 3, 2, 2),
//        listOf(3, 3, 3, 3, 2, 2),
//    )

    var answer = 0
    fun run(arr: List<Int>): Boolean {
        val check = IntArray(n)
        for (i in 0 until n-1) {

            // 커지기
            if (arr[i] == arr[i+1]-1) {
                var count = 1
                var j = i-1
//                println(1)
                check[i] += 1
                while (true) {
//                    println("???")

                    if (j == -1)
                        break

                    if (arr[i] != arr[j])
                        break

                    check[j] += 1
                    count ++
                    j --
                }

                if (count < l) {
                    return false
                }
            }

            // 작아지기
            else if (arr[i]-1 == arr[i+1]) {
//                println(2)
                var count = 1
                var j = i+1
                while (true) {
                    if (j == n)
                        break

                    if (arr[i+1] != arr[j])
                        break

                    check[j] += 1
                    count ++
                    j ++
                }

                if (count < l) {
                    return false
                }
            }

            else if (arr[i] == arr[i+1])
            else return false
        }

//        println("check: ${check.joinToString()}")
        for (c in check) {
            if (c > 1) return false
        }

        println(arr.joinToString())
        return true
    }

    for (i in 0 until n) {
        if (run(graph[i].toMutableList())) answer += 1
    }

    for (i in 0 until n) {
        val myList = mutableListOf<Int>()
        for (j in 0 until n) {
            myList.add(graph[j][i])
        }

        if (run(myList)) answer += 1
    }

//    run(listOf(2,2,3,3,2,2))

    println(answer)
}