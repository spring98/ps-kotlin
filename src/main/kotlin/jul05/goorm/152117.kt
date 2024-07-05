package jul05.goorm

fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()
    val processes = mutableListOf<String>()

    for (i in 0 until n) {
        processes.add(readLine())
    }

    val k = readLine().toInt()
    val studies = mutableListOf<String>()

    for (i in 0 until k) {
        studies.add(readLine())
    }

    val cache = mutableMapOf<String, MutableMap<String, Int>>()

    for (process in processes) {
        for (i in process.indices) {
            val key = process.substring(0, i+1)

            if (cache.containsKey(key)) {
                if (cache[key]!!.containsKey(process)) {
                    cache[key]!![process] = cache[key]!![process]!! + 1
                } else {
                    cache[key]?.set(process, 1)
                }
            } else {
                cache[key] = mutableMapOf()
                cache[key]?.set(process, 1)
            }
        }
    }

    for (study in studies) {
        if (!cache.containsKey(study)) {
            println(0)
        } else {
            val result = cache[study]!!.toList().sortedWith(compareByDescending<Pair<String, Int>> { it.second }.thenBy { it.first }).first()
            println("${result.first} ${result.second}")
        }
    }
}