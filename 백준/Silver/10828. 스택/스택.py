input = __import__('sys').stdin.readline

n = int(input())
stack = []
for i in range(n):
    arr = list(input().split())
    if arr[0] == 'push':
        stack.append(arr[1])
    elif arr[0] == 'pop':
        if len(stack) == 0: print(-1)
        else: print(stack.pop())
    elif arr[0] == 'size':
        print(len(stack))
    elif arr[0] == 'empty':
        if len(stack) == 0: print(1)
        else: print(0)
    else:
        if len(stack) == 0: print(-1)
        else: print(stack[-1])