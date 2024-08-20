import sys
input = sys.stdin.readline

def T_make():
    ans = []
    for i in target:
        ans.append(i)
    
    return ans

string = list(input().rstrip())
target = list(input().rstrip())

index = 0
stack = []

for i in string:
    stack.append(i)

    if stack[-len(target):] == target:
        for _ in range(len(target)):
            stack.pop()

if stack:
    print(*stack, sep="")
else:
    print("FRULA")