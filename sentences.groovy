// SENTENCES
//if
def x = 0
if( x == 1) {
  println x
} else {
  println ""
}

//while
def i = 0
while ( i <= 1) {
  println i
  i++
}

//for
def list = [1,2,3,4]
for( num in list) {
  println num
}

list.each { println it}

//switch
def num = 12.toFloat()
switch(num) {
  case 1:
          println "1"
          break
  case 2:
          println "2"
          break  
  case 1..3:
          println "in range 1..3"
          break
  case [1,2,12]:
          println "num is in list [1,2,12]"
          break  
  case Integer:
          println "num is an integer"
          break
  case Float:
          println "num is float"
          break  
  default:
          println "detault"
}

// ternary operator (expresion) ? true : false
def name = 'Dan'
def isitdan = (name.toUpperCase() == 'DAN') ? 'YES' : 'NO'

def msg
def output = (msg != null) ? msg : 'default message...'
def elvisOutput = msg ?: 'default message ....'

// conditional structure
def validAges = 18..35
def someAge = 19
println someAge in validAges

// break/continue

for( String s in 'a'..'z' ){
  if ( s == 'a') continue
  if ( s > 'b' ) break
}
