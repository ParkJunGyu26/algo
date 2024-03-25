def solution(arr):
    answer = []
    if (len(arr) == 1): 
        answer = arr
    else:
        answer.append(arr[0])
        for i in range(1, len(arr)):
            if (arr[i] != arr[i-1]):
                answer.append(arr[i])
    return answer