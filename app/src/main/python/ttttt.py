import numpy as np
import pandas as pd

st1 = ''
st2 = 'dullness'
st3 = 'weakness'
st4 = ''
st5 = ''
st6 = ''
st7 = ''
st8 = ''
st9 = ''
st10 = ''
st11 = ''
st12 = ''
st13 = ''
st14 = ''
st15 = ''
st16 = ''
st17 = ''
st18 = ''
st19 = ''

l1=['low_milk', 'dullness', 'weakness', 'low_appetite', 'high_fever',
          'distress', 'convulsions', 'breathing_difficulty', 'salivation',
          'swollen_teats', 'abortion', 'swollen_hoof', 'body_discharge',
          'sudden_death', 'swellings', 'lameness', 'ruminations',
          'rapid_pulse', 'stomach_swelling']

disease = ['Anthrax', 'Blackquarter', 'Foot&mouth', 'Rabbies',
               'Blue tongue', 'Brucellosis', 'Listerosis', 'Vibriosis',
               'Mastitis', 'Footrot', 'Etiology', 'IBR', 'Tick fever',
               'East coast fever', 'Milk fever', 'Bloat', 'Distemper',
               'Leptospirosis', 'Calf Diphtheria']

l2 = []

for x in range(0, 19):
       l2.append(0)

# testing data
tt = pd.read_csv("animal_diseases_test.csv")
tt.replace({'Disease': {'Anthrax': 0, 'Blackquarter': 1, 'Foot&mouth': 2, 'Rabbies': 3, 'Blue tongue': 4, 'Brucellosis': 5, 'Listerosis': 6,
                        'Vibriosis': 7, 'Mastitis': 8, 'Footrot': 9, 'Etiology': 10, 'IBR': 11, 'Tick fever': 12, 'East coast fever': 13,
                        'Milk fever': 14, 'Bloat': 15, 'Distemper': 16, 'Leptospirosis': 17, 'Calf Diphtheria': 18}
                 }, inplace=True)
    
x_test = tt.iloc[l1]
y_test = tt[["Disease"]]

# training data
tr = pd.read_csv("animal_diseases1.csv")
tr.replace({'Disease': {'Anthrax': 0, 'Blackquarter': 1, 'Foot&mouth': 2, 'Rabbies': 3, 'Blue tongue': 4, 'Brucellosis': 5, 'Listerosis': 6,
                        'Vibriosis': 7, 'Mastitis': 8, 'Footrot': 9, 'Etiology': 10, 'IBR': 11, 'Tick fever': 12, 'East coast fever': 13,
                        'Milk fever': 14, 'Bloat': 15, 'Distemper': 16, 'Leptospirosis': 17, 'Calf Diphtheria': 18}
                 }, inplace=True)
X = tr[l1]
y = tr[["Disease"]]

from sklearn.naive_bayes import MultinomialNB
gnb = MultinomialNB()
gnb = gnb.fit(X, np.ravel(y))
from sklearn.metrics import accuracy_score
y_predic = gnb.predict(x_test)
print(accuracy_score(y_test, y_predic))
print(accuracy_score(y_test, y_predic, normalize=False))
psymptoms = [st1, st2, st3, st4, st5, st6, st7, st8, st9, st10, st11, st12, st13, st14, st15, st16, st17, st18, st19]

for k in range(0, len(l1)):
       for z in psymptoms:
              if(z == l1[k]):
                l2[k] = 1

inputtest = [l2]
predict = gnb.predict(inputtest)
predicted = predict[0]

h = "no"
for a in range(0, len(Disease)):
       if(Disease[predicted] == Disease[a]):
            h = 'yes'
            break
if(h == 'yes'):
       print(Disease[a])
else:
       print('no disease')

#print(str(Disease[a]))
