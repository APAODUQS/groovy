def x = { num->
	print num
}

def timesTen(num, closure){
	closure(num*10)
}
timesTen(10, { println it })

def greet = {String name, String greeting = "Howdy" -> 
	println "$greeting, $name"
}
greet("Joe")

def concat = { String... args ->
  args.join('')
}

def someMethod(Closure c) {
  println "..."
  println c.maximumNumberOfParameters
  println c.parameterTypes
}

def someClosure = { int x, int y -> x + y }
someMethod (someClosure)

// example with for
List favNums = [2,21,44,35,8,4]

for (num in favNums) {
  println num
}

favNums.each { println it }

for( int i = 0; i < favNums.size(); i++ ) {
    println "$i:${favNums[i]}"
}

favNums.eachWithIndex { num, idx ->
  println "$idx:$num"  
}

// findAll
List days = ["Sunday", "Monday", "Tuesday" ,"Wednesday", "Thursday", "Friday", "Saturday"]
List weekend = days.findAll { it.startsWith("S") }

// collect
List nums = [1,2,2,7,2,8,2,4,6]
List numsTimesTen = []
nums.each { num ->
  numsTimesTen << num * 10
}

List newTimesten = nums.collect { num -> num * 10 }

// curry methods
def log = { String type, Date createdOn, String msg -> 
  println "$createdOn [$type] - $msg"
}
log("DEBUG", new Date(), "This is my first debug statement...")

def debugLog = log.curry("DEBUG")
debugLog(new Date(), "This is another debug statement...")
debugLog(new Date(), "This is one more...")

// right curry
def crazyPersonLog = log.rcurry("Why am I logging this way...")
crazyPersonLog("ERROR", new Date())

// index based curry
def typeMsgLog = log.ncurry(1, new Date())
typeMsgLog("ERROR", "This is using curry")

// exercise
def myMethod(Closure c) {
    c()
}
def foo = { println "foo..."}
myMethod(foo)

Map teams = [baseball: "Cleveland Indians", basketball: "Cleveland Cavs", football: "Cleveland Browns"]
teams.eachs { k, v -> 
  println "$k = $v"
}

// map and list methods
List people = [
  [name: "Jane", city: "New York City"],
  [name: "Tom", city: "Cleveland"]
]
people.find { person -> person.city == "Cleveland" }
people.findAll { person -> person.city == "Cleveland" }
people.any { person -> person.city == "Cleveland" }
people.every { person -> person.city == "Cleveland" }
people.every { person -> person.name.size()  >= 3 }

def peopleByCity = people.groupBy { people -> people.city }
def newYorkers = peopleByCity["New York City"]
def clevelanders = peopleByCity.Cleveland
