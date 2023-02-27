package machines

import regex._
import dfa._

extension (r: RegularLanguage)
    def ||(other: RegularLanguage) = Union(r, other)
    def ~(other: RegularLanguage) = Concat(r, other)
    def <*> = Star(r) 
    def <+> = Concat(r, r <*>)
    def apply(n: Int): RegularLanguage = if n == 0 then Star(r) 
                                          else Concat(r, r{n-1})
    def toDFA(using a: Set[Char]): DFA =
        regexToDFA(r, a)

given Conversion[Char, RegularLanguage] = Character(_)

given Conversion[String, RegularLanguage] = s =>
    simplify( //simplify out the unnecessary complexity
        s.foldRight(Concat(Epsilon, Epsilon)) //Scala requires foldright result as same type
        ((c, rest) => Concat(Character(c), rest))
    )

def getChars(r: RegularLanguage): Set[Char] =
    r match 
        case Union(l1, l2) => getChars(l1) ++ getChars(l2)
        case Concat(l1, l2) => getChars(l1) ++ getChars(l2)
        case Star(l) => getChars(l)
        case Character(c) => Set(c)
        case _ => Set()

given Conversion[RegularLanguage, DFA] = r =>
    given Set[Char] = getChars(r)
    r toDFA



                        
