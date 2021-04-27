def main(st1, st2, st3, st4, st5, st6, st7, st8, st9, st10, st11, st12, st13, st14, st15, st16, st17, st18, st19):
       import numpy as np
       import pandas as pd

       sym1 = str(st1)
       sym2 = str(st2)
       sym3 = str(st3)
       sym4 = str(st4)
       sym5 = str(st5)
       sym6 = str(st6)
       sym7 = str(st7)
       sym8 = str(st8)
       sym9 = str(st9)
       sym10 = str(st10)
       sym11 = str(st11)
       sym12 = str(st12)
       sym13 = str(st13)
       sym14 = str(st14)
       sym15 = str(st15)
       sym16 = str(st16)
       sym17 = str(st17)
       sym18 = str(st18)
       sym19 = str(st19)

       l1 = ['low_milk','dullness','weakness','low_appetite','high_fever','distress','convulsions','breathing_difficulty','salivation',
       'swollen_teats','abortion','swollen_hoof','body_discharge','sudden_death','swellings','lameness','ruminations',
       'rapid_pulse','stomach_swelling']

       disease = ['Anthrax', 'Blackquarter', 'Foot&mouth', 'Rabbies',
                     'Blue tongue', 'Brucellosis', 'Listerosis', 'Vibriosis',
                     'Mastitis', 'Footrot', 'Etiology', 'IBR', 'Tick fever',
                     'East coast fever', 'Milk fever', 'Bloat', 'Distemper',
                     'Leptospirosis', 'Calf Diphtheria']

       l2 = []

       for x in range(0, len(l1)):
              l2.append(0)

       # testing data
       #tt = pd.read_csv("animal_diseases_test.csv")
       from os.path import dirname,join
       datatest = join(dirname(__file__), "animal_diseases_test.csv")
       tt = pd.read_csv(datatest)
       tt.replace({'Disease': {'Anthrax': 0, 'Blackquarter': 1, 'Foot&mouth': 2, 'Rabbies': 3, 'Blue tongue': 4, 'Brucellosis': 5, 'Listerosis': 6,
                            'Vibriosis': 7, 'Mastitis': 8, 'Footrot': 9, 'Etiology': 10, 'IBR': 11, 'Tick fever': 12, 'East coast fever': 13,
                            'Milk fever': 14, 'Bloat': 15, 'Distemper': 16, 'Leptospirosis': 17, 'Calf Diphtheria': 18}
                     }, inplace=True)

       X_test= tt[l1]
       y_test = tt[["Disease"]]

       # training data
       #tr = pd.read_csv("animal_diseases1.csv")
       from os.path import dirname, join
       datatrain = join(dirname(__file__), "animal_diseases1.csv")
       tr = pd.read_csv(datatrain)
       tr.replace({'Disease': {'Anthrax': 0, 'Blackquarter': 1, 'Foot&mouth': 2, 'Rabbies': 3, 'Blue tongue': 4, 'Brucellosis': 5, 'Listerosis': 6,
                            'Vibriosis': 7, 'Mastitis': 8, 'Footrot': 9, 'Etiology': 10, 'IBR': 11, 'Tick fever': 12, 'East coast fever': 13,
                            'Milk fever': 14, 'Bloat': 15, 'Distemper': 16, 'Leptospirosis': 17, 'Calf Diphtheria': 18}
                     }, inplace=True)
       X = tr[l1]
       y = tr[["Disease"]]

       from sklearn.naive_bayes import MultinomialNB
       gnb = MultinomialNB()
       gnb = gnb.fit(X, np.ravel(y))
       psymptoms = [sym1, sym2, sym3, sym4, sym5, sym6, sym7, sym8, sym9, sym10, sym11, sym12, sym13, sym14, sym15, sym16, sym17, sym18, sym19]

       for k in range(0, len(l1)):
              for z in psymptoms:
                     if(z == l1[k]):
                            l2[k] = 1
       inputtest = [l2]
       predict = gnb.predict(inputtest)
       predicted = predict[0]

       h = "no"
       for a in range(0, len(disease)):
              if(disease[predicted] == disease[a]):
                     h = 'yes'
                     break
       if(h == 'yes'):
              return(disease[a])
       else:
              return('no disease')

