li = []

n = int(input())

for i in range(n):
    name, ko, en, math = input().split()
    li.append([name, int(ko), int(en), int(math)])

sort_li = sorted(li, key = lambda x : [-x[1], x[2], -x[3], x[0]])

for i in sort_li:
    print(i[0])