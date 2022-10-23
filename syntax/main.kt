// Calculating the rsa alglorithm
class Rsa (primep:Int,primeq:Int) {
var p:Int
var q:Int
var pq:Int
var n:Int
init {
  p = primep
  q = primeq
  pq = p * q
  n = (p-1) * (q-1)
}
  // input should be where there user wants range of prime number to be selected from
  fun calculate_e(x:Int):Int {
    var flag = false
    if (x < 2){
        throw Exception ("invalid number calculate_e $x")
      }else{
        var gcd = gcd(x,n)
        if( gcd == 1) {
           return x
          } else {
            return checkPrime((x*x)+2)
          }
    }  
    return 0
  }

  // used to calculate private key 
  fun calculate_d(e:Int):Int {
   // formula for calculating d = ((n*i) +1)/e
   // i is unknown and will help us get d
   // d should have a modulus of 0
  var number = 0
  var d = 0
  while (number < 1) {
  number ++
  var wegotd = ((n*number)+1)/e  
  if (wegotd % e == 0){
    d = wegotd
    break
    } else{
        calculate_d(e+1)
      }
  }
  return d
  }
}

fun gcd(x:Int,y:Int):Int {
  var e = x
  if (y == 0){
      return x
    }else{
      return gcd(y,x%y)
  }
}

fun checkPrime(num:Int):Int { 
    var flag = false
    var rsa = Rsa(11,13)
    for (i in 2..num / 2) {
        // condition for nonprime number
        if (num % i == 0) {
            flag = true
            break
        } else{
                var e = rsa.calculate_e(num)
        if (e > 1 && e < ((11-1) * (13-1))) {
          return e
        }
      }
    }
    return rsa.calculate_e(29)
}

fun main() {
  fun double_var(arg : Double) {
    println(arg)
  }
    for (i in 2..20){
     var e = checkPrime(i)
     println(e)
    }
  var x = 42.2
  double_var(x)
}

