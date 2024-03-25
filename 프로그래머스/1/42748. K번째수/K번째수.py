def solution(array, commands):
    answer = []
    for a in commands:
        i, j, k = a[0], a[1], a[2]
        li = sorted(array[i-1:j])
        answer.append(li[k-1])
    return answer