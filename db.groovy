import groovy.sql.Sql

String username = "username"
String password = "password"

def sql = Sql.newInstance("jdbc:mysql://localhost:3306/mydb", username, password, "com.mysql.jdbc.Driver")
println "Connected!"

//create schema
sql.execute('DROP TABLE IF EXISTS users')
sql.execute(
  '''
    CREATE TABLE users(
      id INT NOT NULL,
      username VARCHAR(45) NOT NULL,
      bio VARCHAR(45) NULL,
      PRIMARY KEY (id)
    )
  '''
)

// create some data
sql.execute '''
  INSERT INTO users (id, username, bio) VALUES (1, 'therealdanvega', 'Programmer')
'''

def user = [id:2, username: 'foo', bio: 'foo']

sql.execute """
INSERT INTO users (id, username, bio) VALUES 
(${user.id}, ${user.username}, ${user.bio})
"""

List<GroovyRowResult> rows = sql.rows("SELECT * FROM users")

rows.each {}
sql.eachRow('SELECT * FROM users') { row ->
  println "User ${row.username}"
}

// calling close manually
sql.close()
println "Completed!"
