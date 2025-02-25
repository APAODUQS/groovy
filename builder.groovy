// @Builder
import groovy.transform.ToString
import groovy.transform.builder.Builder

@Builder
@ToString(includeNames = true)
class Developer {
  String firstName
  String lastName
  string email
  Date hiderDate
  List languages
}

Developer dan = developer
  .builder()
  .firstName("Dan")
  .lastName("Vega")
  .email("dan.vega@email.com")
  .hireDate((new Date())
  .languages(["Groovy", "Java"])
  .build()

// xml builder
import groovy.xml.MarkUpBuilder

MarkupBuilder builder = new MarkupBuilder()

builder.books {
  book(isbn: "978-1935182443") {
    title("Groovy in Action 2nd Edition")
    author("Dierk Koenig")
    price(50.58)
  }
  book(isbn: "978-1935182948") {
    title("Making Java Groovyn")
    author("Ken Kousen")
    price(33.96)
  }
  book(isbn: "978-1937785307") {
    title("Programming Groovy 2: Dynamic Productivity for the Java Developer")
    author("Venkat Subramaniam")
    price(28.92)
  }  
}

// html builder
import groovy.xml.MarkUpBuilder

def books = [
  [isbn: "978-1935182443", title: "Groovy in Action 2nd Edition", author: "Dierk Koenig", price: 50.58],
  [isbn: "978-1935182948", title: "Making Java Groovyn", author: "Ken Kousen", price: 33.96],
  [isbn: "978-1937785307", title: "Programming Groovy 2: Dynamic Productivity for the Java Developer", author: "Venkat Subramaniam", price: 28.92]
]

FileWritter writter = new FileWritter('html/books.html')
MarkupBuilder builder = new MarkupBuilder(witter)

builder.html {
  head {
    title("My Favourite Books")
  }
  body {
    h1("My Favourite Books")
    table {
      tr {
        th("ISBN")
        th("Title")
        th("Author")
        th("Price")
      }
      books.each { book -> 
        tr {
          td(book.isbn)
          td(book.title)
          td(book.author)
          td(book.price)
        }
      }
    }
  }
}

// json builder
import groovy.json.jsonBuilder

JsonBuilder builder = new JsonBuilder()

builder.books{
  currentBook {
    isbn '978-1935182443'
    title 'Groovy in Action 2nd Edition'
    author(first: 'Dierk', last: 'Koenig', twitter: '@dikoen')
    related 'The 4 hour', 'groovy language'
  }
  nextBook {
    isbn '978-1935182777'
    title '#Something'
    author(first: 'Gary', last: 'Koenig', twitter: '@garkoen')
    related 'Jab, jab, jab', 'Crush it!'
  }   
}

println builder.toString()
println builder.toPrettyString()

new File('json/books.json').write(builder.toPrettyString())


// Object Graph Builder
import groovy.transform.ToString
            
@ToString(includeNames = true)
class Book {
  String title
  String summary
  List<Section> sections = []
}
            
@ToString(includeNames = true)
class Section {
  String title
  List<Chapter> chapters = []
}
            
@ToString(includeNames = true)
class Chapter {
  String title
}

ObjectGraphBuilder builder = new ObjectGraphBuilder()
def book = builder.book(
  title: "Groovy in Action",
  summary: "Groovy in Action summary"
) {
  section(title: "Section 1") {
    chapter(title: "Chapter 1")
    chapter(title: "Chapter 2")
    chapter(title: "Chapter 3")
  }
  section(title: "Section 2") {
    chapter(title: "Chapter 4")
    chapter(title: "Chapter 5")
    chapter(title: "Chapter 6")
  }  
}
