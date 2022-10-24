// Calculating the rsa alglorithm
class Rsa(primep: Int, primeq: Int) {
    var p: Int
    var q: Int
    var pq: Int
    var n: Int
    init {
        p = primep
        q = primeq
        pq = p * q
        n = (p - 1) * (q - 1)
    }

    // input should be where there user wants range of prime number to be selected from
    fun calculate_e(x: Int): Int {
        var flag = false
        if (x < 2) {
            throw Exception("invalid number calculate_e $x")
        } else {
            // we are going to check if a no. is prime according to range given
            // we append the prime values to an array
            // check the gcd of the prime values
            // we also append the prime values with a gcd of one to an array
            // pick the lowest value with gcd of one from the array to reduce working with large values
            // we've gotten e
            var primeno_array = intArrayOf(0)
            for (i in 2..x) {
                primeno_array = checkPrime(i)
                for (no in primeno_array) {
                    if (no >= 2) {
                        var (isgcd, value) = gcd(no, n)
                        if (isgcd) {
                            return value
                        }
                    }
                }
            }
        }
        return 0
    }

    // used to calculate private key
    fun private_key(e: Int): Int {
        // formula for calculating d = ((n*i) +1)/e
        // i is unknown and will help us get d
        // d should have a modulus of 0
        var number = 1
        var d = 0
        while (number < 100) {
            if ((((n * number) + 1) % e) == 0) {
                return ((n * number) + 1) / e
            }
            number++
        }
        return d
    }
}

// getting the gcd of two values and the gcd should be one
// for a number to have a gcd of one the division of the two number
// should give you a modulus of one which is not 0.
fun gcd(smallvalue: Int, largevalue: Int): Pair<Boolean, Int> {
    if (largevalue % smallvalue != 0) {
        return Pair(true, smallvalue)
    }

    return Pair(false, smallvalue)
}

// NOTE:Decpracate this not in use
// getting the lowest value of the IntArray
fun lowestvalue(args: IntArray): Int {
    val mutableArray = args.toMutableList()
    return mutableArray.min()
}

// checking which values are primeno and appending them to an array
fun checkPrime(num: Int): IntArray {
    var flag = false
    var primenumbers: IntArray = intArrayOf(0)
    for (i in 2..num / 2) {
        // condition for nonprime number
        if (num % i == 0) {
            flag = true
        }
    }
    if (!flag) {
        var x = addElement(primenumbers, num)
        return x
    }
    return primenumbers
}

// add values to an IntArray
fun addElement(arr: IntArray, element: Int): IntArray {
    val mutableArray = arr.toMutableList()
    mutableArray.removeIf { x -> x in 0..1 }
    mutableArray.add(element)
    return mutableArray.toIntArray()
}

fun main() {
    fun double_var(arg: Double) {
        println(arg)
    }
    var rsa = Rsa(11, 13)
    var value = rsa.calculate_e(20)
    println(value)
    var another_one = rsa.private_key(value)
    println(another_one)
    var x = 42.2
    double_var(x)
}
