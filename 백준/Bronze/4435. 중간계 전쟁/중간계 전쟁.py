T = int(input())
for i in range(T):
    G_sum = 0
    S_sum = 0
    a, b, c, d, e, f = map(int, input().split())
    A, B, C, D, E, F, G = map(int, input().split())
    G_sum = G_sum + a*1 + b*2 + c*3 + d*3 + e*4 + f*10
    S_sum = S_sum + A*1 + B*2 + C*2 + D*2 + E*3 + F*5 + G*10
    if (G_sum > S_sum):
        print("Battle {}: Good triumphs over Evil". format(i+1))
    elif (G_sum < S_sum):
        print("Battle {}: Evil eradicates all trace of Good". format(i+1))
    else:
        print("Battle {}: No victor on this battle field". format(i+1))