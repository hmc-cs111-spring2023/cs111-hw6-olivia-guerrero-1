package machines

import regex._
import dfa._

given Conversion[Char, RegularLanguage] = Character(_)

given Conversion[String, RegularLanguage] = s =>
    simplify( //Then simplify out the unnecessary complexity
        s.foldRight(Concat(Epsilon, Epsilon)) //Scala requires foldright result as same type
        ((c, rest) => Concat(Character(c), rest))
    )

//given Conversion[String, RegularLanguage] = val charList = _.map(Character)
