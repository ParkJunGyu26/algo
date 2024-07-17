import sys
input = sys.stdin.readline

def sum_max(height):
    cnt = 0
    for i in tree:
        if (i > height):
            cnt += (i-height)
    return m <= cnt

n, m = map(int, input().split())
tree = sorted(list(map(int, input().split())))

# 열린 범위
l, r = 0, max(tree)+1

while l < r:
    mid = (l+r)//2
    if sum_max(mid):
        l = mid + 1
    else:
        r = mid

print(l-1)