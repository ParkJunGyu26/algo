import sys
input = sys.stdin.readline

t = int(input())
for _ in range(t):
    stack = []
    tmp = input().rstrip()
    check = True
    for i in tmp:
        if i == '(':
            stack.append(i)
        else:
            if not stack:
                check = False
                break
            else:
                stack.pop()
    if stack or not check:
        print("NO")
    else:
        print("YES")