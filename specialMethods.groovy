// times
20.times { print ‘-’ }

// upto
1.upto(10) {num -> print num}

/ /downto
10.downto(1) {num -> print num}

// step
0.step(1,0.1) {num -> print num}

// special characters
$/C:\groovy\dan\foo/$

// operators
// =~ find operator - regex matcher
// ==~ match operator
// ~string pattern operator - create pattern instance

// regular expression
// abc - contain that match
// b[aeiou]t - matches bat, bet, bit, bot ,but
// <TAG\b[^>]*>(.?*)</TAG> - matches HTML tag
// \b[A-Z0-9._%+-]@[A-Z0-9.-]+\.[A-Z]{2,}\b - matches email

// patterns
Pattern pattern = Pattern.compile("a\\\\b")
string a = /a\b/
string b = $/http://something.com/something/$
def c = ~/a\b/
