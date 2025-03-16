#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> A, vector<int> B) {
    int answer = 0;
    sort(A.begin(), A.end());
    sort(B.begin(), B.end());
    
    for (int i = A.size()-1; i >= 0; i--) {
        if (A[i] < B.back()) {
            answer++;
            B.pop_back();
        }
    }
    
    return answer;
}