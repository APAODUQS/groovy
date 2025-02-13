def foo() {
  // do stuff
  throw new Exception("Foo Exception")
}

List log = []

try {
  foo()
} catch( Exception e)  {
    log << e.message
} finally {
    log << 'finally'
}
