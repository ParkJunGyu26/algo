import sys
input = sys.stdin.readline

n, m, l = map(int, input().split())
rest = list(map(int ,input().split())) + [l]
rest.sort()
left, right = 1, l+1

while left < right:
    mid = (left + right) // 2
    cnt = 0
    idx = 0
    now = 0
    while cnt <= m and idx <= n:
        if rest[idx] <= now + mid:
            # 다음 휴게소 까지 갈 수 있음
            now = rest[idx]
            idx += 1
        else:
            # 다음 휴게소까지 못 감
            now += mid
            cnt += 1
    if cnt > m:
        left = mid + 1
    else:
        right = mid

print(left)