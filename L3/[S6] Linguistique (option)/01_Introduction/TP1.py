import os
import textstat

# size of file
octets = os.path.getsize('page.txt')
print("octets : ", octets)



with open(r'page.txt', 'r') as file:
    text = file.read()
    print("sentences : ", textstat.sentence_count(text))
    print("ascii chars : ", textstat.char_count(text))
    print("words : ", len(text.split()))

