package machines

import regex._
import dfa._

given Conversion[Char, RegularLanguage] = Character(_)

given Conversion[String, RegularLanguage] = s =>
    simplify( //Then simplify out the unnecessary complexity
        s.foldRight(Concat(Epsilon, Epsilon)) //Scala requires foldright result as same type
        ((c, rest) => Concat(Character(c), rest))
    )

extension (r: RegularLanguage)
    def ||(other: RegularLanguage) = Union(r, other)
    def ~(other: RegularLanguage) = Concat(r, other)
    def * = Star(r) 
    def + = Concat(r, r.*)
    def apply(n: Int): RegularLanguage = if n == 0 then Star(r) 
                                          else Concat(r, r{n-1})
                        

//given Conversion[String, RegularLanguage] = val charList = _.map(Character)
