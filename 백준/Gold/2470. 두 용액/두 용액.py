import sys
input = sys.stdin.readline

value = sys.maxsize
n = int(input())
li = list(map(int, input().split()))
li.sort()
left, right = 0, n-1

while (left < right):
    diff = li[right] + li[left]
    if abs(diff) < value:
        value = abs(diff)
        ans = [li[left], li[right]]
    
    if diff < 0:
        left += 1
    elif diff > 0:
        right -= 1
    else:
        break

ans.sort()
print(*ans)