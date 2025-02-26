// create new file
def file = new File('dummy.txt')
file.write("This is some text\n")
// new File('dummy.txt').text = "..."

file.append("I am some more text...\n")

List lines = file.readLines()
lines.each { line -> 
  println line
}

// list all files in a directory
Strign dir = '/my-dir'
List hidden = []
new File(dir).eachFile { file -> 
  if( file.isFile() ) {
    println file.name
  }
  if( file.isHidden() ) {
    hidden << file.name
  }
}

// list all subfolders in a directory
Strign dir = '/my-dir'
new File(dir).eachDir { subDir -> 
  if( subDir.isDirectory() ) {
    print subDir
  }
}

// create new dir
new File('dummy').mkdir()
new File('one/two/three').mkdirs()
new File('dummy').deleteDir()
