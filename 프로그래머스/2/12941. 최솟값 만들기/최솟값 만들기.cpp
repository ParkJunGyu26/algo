#include <iostream>
#include <algorithm>
#include <vector>
#include <climits>

using namespace std;

int solution(vector<int> A, vector<int> B)
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    int answer = 0;
    sort(A.begin(), A.end());
    sort(B.begin(), B.end());
    
    for (int i = 0; i < A.size(); i++)
        answer += A[i] * B[B.size()-1-i];

    return answer;
}