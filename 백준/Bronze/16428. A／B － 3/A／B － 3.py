a, b = map(int, input().split())
if a%b >= 0:
    print(a//b, a%b, sep='\n')
else:
    div = a//b+1
    print(div, a-b*div, sep='\n')