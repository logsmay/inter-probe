import re

class LetterFilter:

    vowels = ['a', 'e', 'i', 'o', 'u']

    # default constructor
    def __init__(self, text):
        self.text = text

    # method to filter constraints
    # 1) to check if incoming data is lowercase
    # 2) text contains atleast one vowel & one consonants
    def filter_constraints(self):
      constraint_flags = []
      # constraint 1
      if re.match('([a-z]+)$', self.text):
        constraint_flags.append(True)
      else:
        constraint_flags.append(False)
      #constraint 2a
      #if string contains atleast one vowel
      if any(s in self.text for s in self.vowels):
        constraint_flags.append(True)
      else:
        constraint_flags.append(False)
      #constraint 2b
      #if string contains atleast one consonant
      if any(s not in self.vowels for s in self.text):
        constraint_flags.append(True)
      else:
        constraint_flags.append(False)
      return all(constraint_flags)

    # method to remove vowels following strict constraints
    def filter_vowels(self):
      constraint_flag = self.filter_constraints()
      if constraint_flag:
        filter_vowels = [i for i in list(self.text) if i not in self.vowels]
        return ''.join(filter_vowels)
      else:
        return 'invalid constraints'

    # method to remove consanants following strict constraints
    def filter_consonants(self):
      constraint_flag = self.filter_constraints()
      if constraint_flag:
        filter_consonants = [i for i in list(self.text) if i in self.vowels]
        return ''.join(filter_consonants)
      else:
        return 'invalid constraints'

if __name__ == "__main__":
    data = input("Enter Input Text:")
    # creating object of the class
    obj = LetterFilter(data)
    # calling the instance method using the object obj
    print("filter_vowels() --> " + obj.filter_vowels())
    print("filter_consonants() --> " + obj.filter_consonants())
