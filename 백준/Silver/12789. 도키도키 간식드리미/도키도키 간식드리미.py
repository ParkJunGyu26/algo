import sys

input = sys.stdin.readline

n = int(input())
li = list(map(int, input().split()))

line = [li[i] for i in range(n-1, -1, -1)]
daegi = []

nums = sorted(li, reverse=True)


while line:
    
    if line[-1] == nums[-1]:
        nums.pop()
        line.pop()
    else:
        daegi.append(line.pop())
    
    while daegi:
        if daegi[-1] == nums[-1]:
            daegi.pop()
            nums.pop()
        else:
            break

if daegi:
    print("Sad")
else:
    print("Nice")