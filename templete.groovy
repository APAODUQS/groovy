import groovy.text.SimpleTemplateEngine
import groovy.text.Template

SimpleTemplateEngine engine = new SimpleTemplateEngine(true)
Template template = engine.createTemplate( new File('dynamic.txt') )

// data bindings
Map bindings = [
  firstName: "Dan",
  lastName: "Vega",
  commits: 27,
  repositories: [
    [name: 'Apache Groovy Course 1', url: "https://github.com/something1"],
    [name: 'Apache Groovy Course 2', url: "https://github.com/something2"],
    [name: 'Apache Groovy Course 3', url: "https://github.com/something3"],
  ]
]

println template.make(bindings)

// template.txt:
/* 
Dear $firstName $lastName,
This is the notification, you have ${repositories.size()} repositories on Github and you had a total of $commits commits this month.

Here are your respositories:
<% repositories.each { repo ->
  println "\t $repo.name " 
}

Thanks
*/
