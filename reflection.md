# Reflection on implementing regular expressions of a DSL

## Which operators were easiest to implement and why?
It was really easy to implement the shorthands for union/concat/star and +, because they didn't have to fight against any part of scala - it was trivial to use functions whose names begin (and only comporimise) special characters. The conversions with character and string were also simple because they mainly worked entirely in the bounds of what scala gives us, although effectively dealing with the types in string->reglang was somewhat frustrating.

## Which operators were most difficult to implement and why?
The string->reglang operator ended up difficult to implement due to errors with keeping consistent types in both foldRight and reduce. I ended up having to include a starting regular language of ε~ε in order to keep types consistent, then later simplify it out of the output.

## Comment on the design of this internal DSL
  It seems *okay* to write out regular languages, although it could definitely be nicer. Being able to write things out as strings and characters rather than just objects makes it appear much neater. I think it would be really nice if we could explicitly parse out special characters in strings, to allow DSLs comprised of only strings to be able to have more than concat and characters.

Write a few brief paragraphs that discuss:

- What works about this design? (For example, what things seem easy and
  natural to say, using the DSL?)
- What doesn't work about this design? (For example, what things seem
  cumbersome to say?)
- Think of a syntactic change that might make the language better. How would
  you implement it _or_ what features of Scala would prevent you from
  implementing it? (You don't have to write code for this part. You could say
  "I would use extension to..." or "Scala's rules for valid
  identifiers prevent...")
