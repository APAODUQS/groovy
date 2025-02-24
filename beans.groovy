// Call variables from clases: setters & getters
class MyClass {

  public Integer value

  void setvalue(value) {
    this.value = value
  }
  
  void getValue() {
    value * 2
  }

}

MyClass myClass = new MyClass()
myClass.myVariable = 100
println myClass.myVariable //print 200
println myClass.@myVariable //print 100

// Patterns
// Get Mentions @ /\B@[a-z0-9_-]+/
// Get HashTags # ~/(?:s1\A)[##]+[(A-Za-z0-9-_]+)/
