from itertools import combinations as cb

li = []

for i in range(9):
    height = int(input())
    li.append(height)

li.sort()

for x in cb(li, 7):
    if sum(x) == 100:
        print('\n'.join(map(str, x)))
        break