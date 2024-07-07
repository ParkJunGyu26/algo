import sys
from collections import deque
input = sys.stdin.readline

N, Kim, Lim = map(int, input().split())
people = deque([i for i in range(1, N+1)])

round = 0
# print("people : ", people)
while (True):
    if Kim not in people or Lim not in people:
        break

    round += 1

    change = []
    while people:
        p1 = people.popleft()
        if len(people) == 0:
            change.append(p1)
            break
        p2 = people.popleft()
        if (p1 == Kim or p1 == Lim) and (p2 == Kim or p2 == Lim):
            print(round)
            exit(0)

        if p1 == Kim or p1 == Lim:
            change.append(p1)
            continue

        if p2 == Kim or p2 == Lim:
            change.append(p2)
            continue

        change.append(p1)
    
    people = deque(change)
    # print("people : ", people)
    # print("-----")

print(round)