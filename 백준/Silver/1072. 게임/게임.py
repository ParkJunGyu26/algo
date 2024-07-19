x, y = map(int, input().split())
z = (y * 100) // x

if z >= 99:
    print(-1)
else:
    l, r = 0, x
    while l < r:
        mid = (l+r) // 2

        if (y + mid) * 100 // (x + mid) != z:
            r = mid
        else:
            l = mid + 1
    print((l+r)//2)