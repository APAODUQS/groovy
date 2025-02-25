// REST Services

// Work with xml
def xml = '''
  <sports>
    <sport>
      <name>Football</name>
    <s/port>
  </sports>
'''
def sports = new XmlSlurper().parseText(xml)
println sports.getClass().getName()
println sports.sport.name
// with a file
def sports = new XmlSlurper().parse('data/sports.xml')

// Workin with JSON
def books = '''
{
  "books" : {
    "currentBook": {
      "title": "Book Totle",
      "isbn": "242-464-4534543-32",
      "author": {
        "first": "Author",
        "last": "Name",
        "twitter": "@author"
      },
      "related": [
        "tag",
        "bla bla bla"
      ]
    }
  }
}
'''

JsonSlurper slurper = new JsonSlurper()
def json = slurper.parseText(books)
println json
println json.getClass().getName() //LazyMap
println json.books.currentBook.title
//with a file
def json = slurper.parse(new File('data/books.json'))


// Call APIs
import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient

@Grab('org.codehaus.groovy.modules.http-builder:http-builder:0.7.1')

String base = 'http//api.icdb.com'

def chuck = new RESTClient(base)
def params = [fristName: "Dan", lastName: "Vega"]

chuck.contentType = ContentType.JSON
chuck.get( path; 'jokes/random', query: params ) { reponse -> json
  println reponse.status
  printls json
}
