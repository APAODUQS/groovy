class PropertyDemo {

  def prop1 = "prop1"
  def prop2 = "prop2"
  def prop3 = "prop3"

  def getProperty(String name){
    println "getProperty() called with the argument $name"

    if ( metaClass.hasProperty(this.name) ) {
      return metaclass.getProperty(this.name) // If we dont return this, this returns null
    } else {
      println "lets do somethibg fun with this property..."
      return "party time ..."
    }
  }
  
}
def pd = new PropertyDemo()

println pd.prod1
println pd.prod2
println pd.prod3
println pd.prod4

//Customizing MOP
//You can intercept write access to properties by overriding the setProperty() method:

class POGO {

  String property

  void setProperty(String anme, Object value) {
    this.@"name" = 'overridden'
  }
  
}

def pogo = new POGO()
pogo.property = 'a'

assert pogo.property == 'overridden'

//Missing method:
// groovy supports the concept of methodMissing. This method differs from invokeMethod in that it is only invoked in case of a failed method dispatch, when no method can be found for the given name and/or arguments
class MyMissingClass {

  def methodMissing(String name, def args){
    println "Method Missing called on: $name"
    println "with the arguments: $args"
    // another option is:
    // throw new MissingMetodException(name, args)
  }
  
}

myMissingClass = new MyMissingClass()
myMissingClass.someMethod(1,2,3)

// without class use metaclass
class Developer {}

Developer dan = new Developer()
dan.metaclass.name = 'Dan'
dan.metaclass.writeCode = { -> println "$name loves to write code..."}
dan.writeCode()

// every instance
String.metaclass.shout = { -> toUpperCase() }
println "hello dan".shout()

// use Category
import.groovy.time.TimeCategory
use(TimeCategory) {
  println 1.minute.from.now
}

// Exercise
class Developer {
  
  String first
  String last
  Stirng email
  List languages

  def invokeMethod(String name, Object args){
    println "invokedMethod() called sith the arguments $args"
  }

  def getProperty(String property) {
    println "getProperty called ... "
    metaclass.getproperty(this.property)
  }

  def setProperty(String property, Object newValue) {
    println "setProperty called with the name $property and the new value $nweValue"
    metaclass.setproperty(this.property, newValue)
  }
  
}

Developer developer = new Developer(first: "Dan", last: "Evan", email: "dan_evan@email.com")
developer.writeCode("Groovy")
println developer.first
developer.languages = ["Groovy", "Java"]

// @Sortable to sort
// @TypeChecked to check the typo on groovy
