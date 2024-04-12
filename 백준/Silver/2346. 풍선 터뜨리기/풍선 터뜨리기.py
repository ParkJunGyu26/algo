from collections import deque

N = int(input())
dq = deque(enumerate(map(int, input().split())))
result = [] 

while dq:
    idx, number = dq.popleft()
    result.append(idx+1)
    
    if number > 0:
        dq.rotate(-(number-1))
    else:
        dq.rotate(-number)
        
print(" ".join(map(str, result)))