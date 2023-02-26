package machines

import regex._
import dfa._

given Conversion[Char, RegularLanguage] = c => Character(c)
