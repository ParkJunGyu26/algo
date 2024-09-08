import sys
import copy
import pprint
input = sys.stdin.readline

S = input().rstrip()
prefix_sum = [[0] * 26 for _ in range(len(S)+1)]

for i in range(1, len(S)+1):
    prefix_sum[i] = copy.deepcopy(prefix_sum[i-1])
    prefix_sum[i][ord(S[i-1])-ord('a')] = prefix_sum[i-1][ord(S[i-1])-ord('a')] + 1

q = int(input())
for i in range(q):
    tmp = list(input().rstrip().split())
    l, r, a = tmp[0], int(tmp[1]), int(tmp[2])
    print(prefix_sum[a+1][ord(l)-ord('a')] - prefix_sum[r][ord(l)-ord('a')])